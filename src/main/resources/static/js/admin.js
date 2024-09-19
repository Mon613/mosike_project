function icon_delete(id) {
    $.ajax({
        url: "delete/" + id,
        method: "DELETE",
        contentType: "application/json",
        success: function (result) {
            alert(result)
            window.location.href = "getAll";
        },
        error: function (request, msg, error) {
            //handle failure
        }
    })
}

//songs
function icon_delete_song(id) {
    $.ajax({
        url: "deleteSong/" + id,
        method: "DELETE",
        contentType: "application/json",
        success: function (result) {
            alert(result)
            window.location.href = "getAll";
        },
        error: function (request, msg, error) {
            //handle failure
        }
    })
}

function edit_song(id) {
    window.location.href = "update/" + id;
}

//songs end

//album
function searchSongs() {
    var searchTerm = document.getElementById("searchTerm").value;
    // Gửi yêu cầu AJAX để tìm kiếm bài hát
    $.ajax({
        url: "/searchSongs",
        data: {term: searchTerm},
        success: function (data) {
            // Cập nhật danh sách bài hát trong thẻ <select>
            var songList = data.songs;
            var options = "<option value=''>Chọn bài hát</option>";
            for (var i = 0; i < songList.length; i++) {
                options += "<option value='" + songList[i].id + "'>" + songList[i].name + "</option>";
            }
            $("select[th\\:field='*{selectedSongId}']").html(options);
        }
    });
}

function delete_album(id) {
    $.ajax({
        url: "deleteAlbum/" + id,
        method: "DELETE",
        contentType: "application/json",
        success: function (result) {
            alert(result)
            window.location.href = "getAll";
        },
        error: function (request, msg, error) {
            //handle failure
        }
    })
}

//play music
let currentArt = document.getElementById('current_art');
let currentSongTitle = document.getElementById('current_song_title');
// let singers = document.getElementById('singer_song');
let playPauseButton = document.getElementById('ic_play');
let timePlayed = document.getElementById('time_played');
let totalTime = document.getElementById('time_remaining');
let progressBar = document.getElementById('progress_bar');
let repeat = document.getElementById('ic_repeat');
let progressValue = document.getElementById('progress_value');
let audio;
var timerId;
let currentTime = 0;
let duration;

function playAudio(songId) {
    fetch(`/api/songs?id=${songId}`)
        .then(response => response.json())
        .then(response => playMusic(response));
    if (!!audio) {
        audio.pause();
        audio = null;
    }
}

function playMusic(song) {
    console.log(song);
    currentSongTitle.textContent = song.nameSong;
    currentArt.srcset = song.imgSong;
    playPauseButton.classList.remove('bi-play-circle');
    playPauseButton.classList.add('bi-pause-circle');
    audio = new Audio(song.linkSong);
    audio.play();


    audio.addEventListener('loadedmetadata', () => {
        currentTime = 0;
        duration = audio.duration;
        const minutes = Math.floor(duration / 60);
        const seconds = Math.round(duration % 60);
        totalTime.textContent = `${minutes.toString().padStart(2, '0')}:${seconds.toString().padStart(2, '0')}`;
    });

    audio.onpause = e => {
        clearInterval(timerId)
    }
    audio.onplay = e => {
        timerId = setInterval(updateTimePlayed, 1000);
    }

}
const updateTimePlayed = () => {
    currentTime += 1;
    const minutes = Math.floor(currentTime / 60);
    const seconds = Math.round(currentTime % 60);
    timePlayed.textContent = `${minutes.toString().padStart(2, '0')}:${seconds.toString().padStart(2, '0')}`;
    progressBar.value = `${(currentTime / duration) * 100}`;
};


playPauseButton.addEventListener('click', () => {
    if (audio.paused || audio.currentTime <= 0) {
        audio.play();
        audio.currentTime = currentTime;
        playPauseButton.classList.remove('bi-play-circle');
        playPauseButton.classList.add('bi-pause-circle');
    } else {
        audio.pause();
        playPauseButton.classList.remove('bi-pause-circle');
        playPauseButton.classList.add('bi-play-circle');

    }
});
progressBar.onchange = e=>{
    audio.pause();
    console.log(e.target.value);
    currentTime = e.target.value * duration/100;
    audio.currentTime = currentTime;
    audio.play();

}
repeat.addEventListener('click',()=>{
    audio.loop =!audio.loop;
    if (audio.loop){
        repeat.classList.remove('bi-repeat');
        repeat.classList.add('bi-repeat-1');

    }else {
        repeat.classList.remove('bi-repeat-1');
        repeat.classList.add('bi-repeat');
        audio.loop = false;
    }

});
audio.addEventListener('ended',()=>{
    if (!!audio.loop){
        clearInterval(timerId);
        currentTime=0;
        timerId = setInterval(updateTimePlayed, 1000);
    }else {
        audio.pause();
    }
})

//search song
function searchSong(){
    console.log('input')
    window.location.href="searchs?txt="+input;

}
