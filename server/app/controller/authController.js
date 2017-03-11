const user       = require('../model/user');
const task       = require('../model/task');
const statistics = require('../model/statistics');
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
exports.register = function(req, res) {
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
};
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
* @apiSuccess {Bool} success true
* @apiSuccess {String} username username
* @apiSuccess {String} token token
*/
exports.login = function(req, res) {
  user.findOne({
      username : req.body.username,
      password : req.body.password
  }, (err, u)=>{
      if (err) throw err;
      if (!u) res.json({ error: "Username/password sai"});
      else {
          var token = jwt.sign(u, secret, {
              expiresIn: 60*60*24
          });
          res.json({
              success: true,
              username: req.body.username,
              token: token
          });
      }
  })
};
