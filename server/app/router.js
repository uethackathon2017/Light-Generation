const express    = require('express');
const user       = require('./model/user');
const passport   = require('passport');
const task       = require('./model/task');
const statistics = require('./model/statistics');

const aggregateController = require('./controller/aggregateController');

module.exports = (app, passport) => {
    /**
     * @api {get} http://54.169.225.125:3000/ Server checker
     * @apiName CheckServer
     * @apiPermission public
     * @apiGroup Common
     *
     * @apiDescription Check server is running!!!
     *
     * @apiExample Example
     * http://54.169.225.125:3000/
     *
     * @apiSuccess {number} 200
     */
    app.get('/', (req, res) => {
        res.send({ status : 200 });
    });

    //nơi code API
    const apiRouter = express.Router();

    /**
     * @api {get} http://54.169.225.125:3000/api/user/:username get User Info
     * @apiName getUserInfo
     * @apiPermission public
     * @apiGroup User
     *
     * @apiDescription Get all information of an user!!!
     *
     * @apiExample Example
     * http://54.169.225.125:3000/api/user/meodorewan
     *
     * @apiSuccess {ObjectId} _id id of user
     * @apiSuccess {String} username username
     * @apiSuccess {Object} info info of user
     * @apiSuccess {Number} info.age age of user
     * @apiSuccess {String} info.name name of user
     */
    apiRouter.get('/user/:username', (req, res) => {
        const username = req.params.username;

        user.findOne({ username: username }).select('_id username info').exec((err, mess) => {
            if (err) throw err;
            if (mess)
                res.status(200).send(mess);
            else
                res.status(404).send('not found');
        });
    });


    /**
     * @api {get} http://54.169.225.125:3000/api/questions/:username/:number get questions
     * @apiName get questions
     * @apiPermission public
     * @apiGroup User
     *
     * @apiDescription Get a number of questions!!!
     *
     * @apiExample Example
     * http://54.169.225.125:3000/api/questions/meodorewan/10
     *
     * @apiSuccess {ObjectId} _id id of question
     * @apiSuccess {String} category category of question
     * @apiSuccess {Number} level level of question (automatically fit with current user's level)
     * @apiSuccess {Object} question detail of question
     * @apiSuccess {[url]} question.image images
     * @apiSuccess {[url]} question.audio audio
     * @apiSuccess {String} question.text text
     * @apiSuccess {Number} question.answer answer
     * @apiSuccess {Boolean} horizontal sharp
     */
    //lấy ra 'number' tasks trong db một cách ngẫu nhiên
    apiRouter.get('/questions/:userId/:number', (req, res) => {
        const userId   = req.params.userId;
        const noTasks  = Number(req.params.number);

        task.aggregate([{$sample : {size : noTasks}}]).exec((err, mess) => {
            if (err) throw err;
            if (mess)
                res.status(200).send(mess);
            else
                res.status(404).send(`không thể lấy ra ${number} câu hỏi, nên gửi lại số câu hỏi`);
        });
    });

    /**
     * @api {put} http://54.169.225.125:3000/api/user/:username modify user's info
     * @apiName modify user's info
     * @apiPermission private
     * @apiGroup User
     *
     * @apiDescription modify user's infomation!!!
     *
     * @apiExample Example
     * http://54.169.225.125:3000/api/meodorewan
     *
     * @apiParam {Number} age new age
     * @apiParam {String} name new name
     *
     * @apiSuccess {String} status successful
     */
    //Sửa thông tin username
    apiRouter.put('/user/:username', (req, res) => {
        const username = req.params.username;
        const age      = req.body.age;
        const name     = req.body.name;

        user.findOne({
            username: username,
        }, (err, one) => {
            if (err) throw err;
            if (!one) res.send('something err');

            one.info = {
                age : req.body.age,
                name : req.body.name
            }

            one.save( (err) => {
                if (err) throw err;
                //console.log(one);
                res.send({ status : 'successful' });
            });

        });
    });

    /**
     * @api {post} http://54.169.225.125:3000/api/log log user's behavior
     * @apiName log user's behavior
     * @apiPermission private
     * @apiGroup Log
     *
     * @apiDescription log user's answer every questions!!!
     *
     * @apiExample Example
     * http://54.169.225.125:3000/api/log
     *
     * @apiParam {ObjectId} taskId id of question
     * @apiParam {String} category category
     * @apiParam {Number} attempts number of attemptions after a successful answer
     * @apiParam {String} username username
     *
     * @apiSuccess {String} status successful
     */
    apiRouter.post('/log', (req, res) => {
        const taskId   = req.body.taskId;
        const category = req.body.category;
        const attempts = req.body.attempts;
        const username = req.body.username;
        let s = new statistics();
        s.username = username;
        s.taskId = taskId;
        s.category = category;
        s.attempts = attempts;
        s.createdAt = Date.now();
        s.save((err) => {
            if (err) throw err;
            res.status(200).send({ status : 'successful'});
        });
    });


    /**
     * @api {get} http://54.169.225.125:3000/api/log exports user behavior data
     * @apiName export user behavior data
     * @apiPermission private
     * @apiGroup User
     *
     * @apiDescription export data of user!!!
     *
     * @apiExample Example
     * http://54.169.225.125:3000/api/log/meodorewan/comparision
     * http://54.169.225.125:3000/api/log/meodorewan/progression
     *
     * @apiSuccess {[Object]} category tập đếm, nhận biết...
     * @apiSuccess {Number} category.attempts the total number of attemptions before correct answers
     * @apiSuccess {Number} category.correctness the number of correct answers
     */
    apiRouter.get('/log/:username/:typeReq', (req, res) => {
        const typeReq  = req.params.typeReq;
        const username = req.params.username;

        let result = {};
        switch (typeReq) {
            case 'progression':
                statistics.find({
                    username: username,
                }).sort({ category : 1 }).exec((err, mess) => {
                    if (err) throw err;
                    mess.forEach((e) => {
                        if (!result[e.category]) result[e.category] = [];
                        result[e.category].push(e.attempts);
                    });
                    res.send(mess);
                });
                break;
            case 'comparision':
                statistics.find({ username : username }, (err, data) => {
                    if (err) throw err;
                    let result = {};
                    data.forEach((e) => {
                        if (!result[e.category]) result[e.category] = {
                            attempts : 0,
                            correctness : 0,
                        };
                        if (Number(e.attempts)) {
                            result[e.category].attempts += e.attempts;
                            result[e.category].correctness++;
                        }
                    });
                    res.send(result);
                });
                break;
                // statistics.find({
                //     username: username,
                // }).aggregate([
                //     { $match: {username: username,} }, //tìm những logs có username tương ứng
                //     { $group: {                         //aggregate
                //         category: '$category',
                //         attempts: {$sum: '$attempts'},
                //         counts  : {$sum: 1},
                //     } },
                // ]).exec((err, mess) => {
                //     if (err) throw err;
                //     res.send(mess);
                // });
                // break;
            default:
                res.send('not found');
        }
    });


    /**
     * @api {post} http://54.169.225.125:3000/register register new account
     * @apiName register new account
     * @apiPermission public
     * @apiGroup Authentication
     *
     * @apiDescription register new account
     *
     * @apiExample Example
     * http://54.169.225.125:3000/register
     *
     * @apiParam {String} username username
     * @apiParam {String} password password
     * @apiParam {Number} age age
     * @apiParam {String} name name
     *
     * @apiSuccess {String} status successful
     */

    //Nơi authenticate
    const authRouter = express.Router();
    // authRouter.post('/login', passport.authenticate('local', {
    //     successRedirect: '/',
    //     failureRedirect: '/login'
    // }));
    authRouter.post('/register', (req, res) =>{
        var u = new user();
        u.username = req.body.username;
        u.password = req.body.password;
        u.info = {
            age : req.body.age,
            name: req.body.name
        }
        u.save((err)=>{
            if (err) throw err;
            res.json({ status : "successful"});
        });
    });

    /**
     * @api {post} http://54.169.225.125:3000/login login
     * @apiName login
     * @apiPermission public
     * @apiGroup Authentication
     *
     * @apiDescription login
     *
     * @apiExample Example
     * http://54.169.225.125:3000/login
     *
     * @apiParam {String} username username
     * @apiParam {String} password password
     *
     * @apiSuccess {String} username username
     */
    authRouter.post('/login', (req, res)=>{
        user.findOne({
            username : req.body.username,
            password : req.body.password
        }, (err, u)=>{
            if (err) throw err;
            if (!u) res.json({ error: "Username/password sai"});
            else res.json({ username: req.body.username });
        })
    });

    app.use('/api', apiRouter);
    app.use('/', authRouter);
}
