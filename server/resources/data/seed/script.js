const common = '/data/short_tall';

for (let i = 0; i <= 17; i++) {
    let path = `${common}/ques${i}`;
    
    let images = [];
    //for (let j = 0; j <= 1; j++){
        let image;
        if (i >= 10) 
            image = `${path}/hl_higher_0${i}.png`;
        else 
            image = `${path}/recognize_option_${j}_${i}.png`;
        images.push(image);
    //}
    
    let audio = `${path}/voice_${i}.mp3`;
    let text = null;
    let level = 0;
    let category = 'Nhận Biết';
    let answer = null;
    let moreInfo = null;
    
    db.tasks.insert({
        category : category,
        level    : level,
        question : {
            images : images,
            audio : audio,
            text  : text,
            answer : answer,
        },
        moreInfo : null,
    });
}