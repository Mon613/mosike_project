function icon_delete(id) {
    $.ajax({
        url:"delete/"+id,
        method:"DELETE",
        contentType:"application/json",
        success: function (result){
            alert(result)
            window.location.href="getAll";
        },
        error: function (request,msg,error){
            //handle failure
        }
    })
}
//songs
function icon_delete_song(id) {
    $.ajax({
        url:"deleteSong/"+id,
        method:"DELETE",
        contentType:"application/json",
        success: function (result){
            alert(result)
            window.location.href="getAll";
        },
        error: function (request,msg,error){
            //handle failure
        }
    })
}
function edit_song(id){
    window.location.href="update/"+id;
}
//songs end

//album
function searchSongs() {
    var searchTerm = document.getElementById("searchTerm").value;
    // Gửi yêu cầu AJAX để tìm kiếm bài hát
    $.ajax({
        url: "/searchSongs",
        data: { term: searchTerm },
        success: function(data) {
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
        url:"deleteAlbum/"+id,
        method:"DELETE",
        contentType:"application/json",
        success: function (result){
            alert(result)
            window.location.href="getAll";
        },
        error: function (request,msg,error){
            //handle failure
        }
    })
}
//play music
function playAudio(songTitle) {
    fetch(`/api/songs?title=${songTitle}`)
        .then(response => response.json())
        .then(data => {
            let songList = data.map(song => ({
                id: song.id,
                title: song.nameSong,
                artist: song.imgSong,
                urlSong:song.linkSong,
                time:song.duration,
                // duration: formatDuration(song.duration)
            }));

            // Hiển thị danh sách bài hát đã được biến đổi
            console.log(songList);
            music(songList);
        });


}
let  currentArt = document.getElementById('current_art');
let  currentSongTitle = document.getElementById('current_song_title');
let  currentSongArtist = document.getElementById('current_song_artist');
let  likeButton = document.getElementById('like');
let  folderButton = document.getElementById('folder');
let  playPauseButton = document.getElementById('ic_play');
let  timePlayed = document.getElementById('time_played');
let  timeRemaining = document.getElementById('time_remaining');
let  progressBar = document.getElementById('progress_bar');
let  progressValue = document.getElementById('progress_value');
function music(songList){
    songList.forEach(song=>{
        // document.getElementById('current_art').src = song.artist;
        // document.getElementById('current_song_title').textContent= song.title;
        playMusic(song.urlSong,song.title,song.artist);
    })
}

let currentAudioPlayer = null;
function playMusic(url,songTitle, songArtist){
    currentSongTitle.textContent = songTitle;
    currentArt.srcset = songArtist;
    if (currentAudioPlayer) {
        currentAudioPlayer.pause();
        currentAudioPlayer = null;
    }
    let audio = new Audio(url);
    audio.play();
    playPauseButton.classList.remove('bi-pause-circle');
    playPauseButton.classList.add('bi-pause-circle');
    currentAudioPlayer=audio;
    audio.addEventListener('loadedmetadata', () => {
        let duration = audio.duration;
        let currentTime = 0;
        const updateTimePlayed = () => {
            currentTime += 1;
            const minutes = Math.floor(currentTime / 60);
            const seconds = Math.round(currentTime % 60);
            timePlayed.textContent = `${minutes.toString().padStart(2, '0')}:${seconds.toString().padStart(2, '0')}`;
            progressBar.value = `${(currentTime / duration) * 100}`;
            if (timePlayed === duration) {
                audio.pause();
                currentAudioPlayer=null;
                clearInterval(timePlayed);

            }
        };

        const timerId = setInterval(updateTimePlayed, 1000);

        // Cập nhật thời gian còn lại
        const minutes = Math.floor(duration / 60);
        const seconds = Math.round(duration % 60);
        timeRemaining.textContent = `${minutes.toString().padStart(2, '0')}:${seconds.toString().padStart(2, '0')}`;
    });
}