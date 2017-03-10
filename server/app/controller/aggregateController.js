const stat = require('../model/statistics');

function progression(username) {
    let result = {};
    stat.find({
        username: username,
    }).sort({ category : 1 }).exec((err, mess) => {
        if (err) throw err;
        mess.forEach((e) => {
            if (!result[e.category]) result[e.category] = [];
            result[e.category].push(e.attempts);
        });
        console.log(result);
        return result;
    });
}

function comparision(username) {
    let result = {};
    stat.find({
        username: username,
    }).aggregate([
        { $match: {username: username,} }, //tìm những logs có username tương ứng
        { $group: {                         //aggregate
            category: '$category',
            attempts: {$sum: '$attempts'},
            counts  : {$sum: 1},
        } },
    ]).exec((err, mess) => {
        if (err) throw err;
        return mess;
    });
}

module.exports = {
    progression,
    comparision,
};
