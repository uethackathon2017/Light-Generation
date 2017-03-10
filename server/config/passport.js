const passport          = require('passport');
const LocalStrategy     = require('passport-local').Strategy;
const User              = require('../app/model/user');
const express           = require('express');

module.exports = (app) => {
    passport.use('local', new LocalStrategy(
        function (username, password, done) {
            User.findOne({username: username}, function (err, user) {
                if (err) {
                    return done(err);
                }
                if (!user.validatePassword(password)) {
                    return done(null, false, {message: 'Incorrect username.'});
                }

                return done(null, user);
            });
        }
    ));
    passport.serializeUser(function(user, done) {
        done(null, user);
    });

    passport.deserializeUser(function(user, done) {
        done(null, user);
    });
}
