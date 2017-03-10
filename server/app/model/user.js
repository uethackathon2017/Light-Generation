const mongoose = require('mongoose');
const bcrypt   = require('bcrypt-nodejs');

const userSchema = mongoose.Schema({
    username: String,
    password: String,
    info    : mongoose.Schema.Types.Mixed,
});

userSchema.methods.validatePassword = (candidatePwd, callback) => {
    bcrypt.compareSync(this.password, candidatePwd, (err, matched) => {
        if (err) return callback(err);
        callback(null, matched);
    })
};

module.exports = mongoose.model('users', userSchema);
