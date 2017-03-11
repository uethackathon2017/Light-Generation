const user       = require('../model/user');
const task       = require('../model/task');
const statistics = require('../model/statistics');

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
exports.getStart = function(req, res, next) {
    res.json({ status : 200 });
}

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
exports.getUserInfo = function(req, res) {
    const username = req.params.username;

    user.findOne({ username: username }).select('_id username info').exec((err, mess) => {
        if (err) res.send('Error');
        if (mess)
            res.status(200).json(mess);
        else
            res.status(404).json('not found');
    });
}

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
exports.getTasks = function(req, res) {
    const userId   = req.params.userId;
    const noTasks  = Number(req.params.number);

    if (!Number(noTasks)) {
        res.send('số lượng câu hỏi phải là số');
    }

    task.aggregate([{$sample : {size : noTasks}}]).exec((err, mess) => {
        if (err) res.send('Error');
        if (mess)
            res.status(200).json(mess);
        else
            res.status(404).json(`không thể lấy ra ${number} câu hỏi, nên gửi lại số câu hỏi`);
    });
}

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
exports.modifyUserInfo = function(req, res) {
    const username = req.params.username;
    const age      = req.body.age;
    const name     = req.body.name;

    if (!Number(age)) {
        res.send('Error: age should be a number');
    }

    user.findOne({ username: username }, (err, one) => {
        if (err) res.send('Error');
        if (!one) res.send('something err');

        one.info = {
            age : req.body.age,
            name : req.body.name
        }

        one.save( (err) => {
            if (err) res.send('Error');
            res.json({ status : 'successful' });
        });
    });
}

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
exports.log = function(req, res) {
    const taskId   = req.body.taskId;
    const category = req.body.category;
    const attempts = req.body.attempts;
    const username = req.body.username;

    //if (!mongoose.Types.ObjectId.isValid(taskId))
    //    res.send('Error: taskId type should be objectId');
    if (!Number(attempts))
        res.send('Error: attempts should be a positive number');

    let s = new statistics();
    s.username = username;
    s.taskId = taskId;
    s.category = category;
    s.attempts = attempts;
    s.createdAt = Date.now();
    s.save((err) => {
      if (err) res.send('Error');
      res.status(200).json({ status : 'successful'});
    });
};

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
* @apiSuccess {Object} object tập đếm, nhận biết...
* @apiSuccess {Number} object.attempts the total number of attemptions before correct answers
* @apiSuccess {Number} object.createdAt timeline
*/

exports.getLog = function(req, res) {
   const typeReq  = req.params.typeReq;
   const username = req.params.username;

   if (typeReq != 'progression' && typeReq != 'comparision')
       res.send('not found');

   let result = {};
   switch (typeReq) {
       case 'progression':
           statistics.find({
               username: username,
           }, (err, data) => {
               if (err) throw err;

               data.forEach((e) => {
                   if (!result[e.category])
                       result[e.category] = [];
                   result[e.category].push({
                       attempts: e.attempts,
                       createdAt : e.createdAt,
                   });
               });
               for (let prop in result) {
                   result[prop].sort((a , b)=> a.createdAt - b.createdAt);
               }
               res.json(result);
           });
           break;
       case 'comparision':
           statistics.find({ username : username }, (err, data) => {
               if (err) res.send('Error');
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
               for (let prop in result) {
                   let att = result[prop].attempts;
                   let corr = result[prop].correctness;
                   result[prop] = {}; 
                   if (corr == 0)  {
                       result[prop].prob = 0;
                   } else {
                       result[prop].prob = corr/att * 100;
                   }
                   result[prop].total = corr;
               }   
               res.json(result);
           });
           break;
       default:
           res.send('not found');
   }
};

exports.checkToken = function(req, res, next){
    var token = req.body.token || req.query.token || req.headers['x-access-token'];
    if (token){
        jwt.verify(token, secret, (err, decoded)=>{
            if (err){
                return res.json({
                    success: false,
                    message: 'Failed to authenticate token'
                });
            } else {
                req.decoced = decoded;
                next();
            }

        });
    } else {
        return res.status(403).send({
            success: false,
            message: 'No token provided'
        });
    }
}
