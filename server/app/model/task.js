const mongoose = require('mongoose');

const taskSchema = mongoose.Schema({
    _id     : mongoose.Schema.Types.ObjectId,
    category: String,
    level   : Number,
    question: {
        images      : [String],
        audio       : [String],
        text        : String,
        answer      : Number, //trong khoảng [0 .. images.length),
        horizontal  : Boolean,
    },
    moreInfo: mongoose.Schema.Types.Mixed,
});

module.exports = mongoose.model('task', taskSchema);
