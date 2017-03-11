const express    = require('express');
const user       = require('./model/user');
const passport   = require('passport');
const task       = require('./model/task');
const statistics = require('./model/statistics');

const apiController  = require('./controller/apiController');
const authController = require('./controller/authController');

module.exports = (app, passport) => {

    //app.get('/', apiController.getStart);
    //nơi code API

    const apiRouter = express.Router();
    apiRouter.use(apiController.checkToken);
    apiRouter.get('/user/:username', apiController.getUserInfo);
    apiRouter.get('/questions/:userId/:number', apiController.getTasks);
    apiRouter.put('/user/:username', apiController.modifyUserInfo);
    apiRouter.post('/log', apiController.log);
    apiRouter.get('/log/:username/:typeReq', apiController.getLog);

    //Nơi authenticate
    const authRouter = express.Router();
    authRouter.post('/register', authController.register);
    authRouter.post('/login', authController.login);

    app.use('/api', apiRouter);
    app.use('/', authRouter);
}
