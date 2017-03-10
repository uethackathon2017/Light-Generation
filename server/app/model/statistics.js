const mongoose              = require('mongoose');

const statSchema = mongoose.Schema({
    taskId      : mongoose.Schema.Types.ObjectId,
    username    : String,
    category    : String,
    attempts    : Number,
    createdAt   : Number, //timestamp
});

module.exports = mongoose.model('statistics', statSchema);
