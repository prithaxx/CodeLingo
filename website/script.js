const toggleButtons = document.querySelectorAll('.toggle-button');
const videoSource = document.getElementById('video-source');

toggleButtons.forEach(button => {
    button.addEventListener('click', () => {
        const videoSrc = button.getAttribute('data-video');
        videoSource.src = videoSrc;
        videoSource.parentElement.load();
        
        toggleButtons.forEach(btn => {
            btn.classList.remove('active');
        });
        button.classList.add('active');
    });
});

document.addEventListener('DOMContentLoaded', function() {
    const toggleButtons = document.querySelectorAll('.toggle-button');
    const descriptionBox = document.querySelector('.description-box p');

    toggleButtons.forEach(button => {
        button.addEventListener('click', function() {
            toggleButtons.forEach(btn => btn.classList.remove('active'));
            this.classList.add('active');
            const description = this.getAttribute('data-description');
            descriptionBox.textContent = description;
        });
    });
});

function scrollToFunction(id, link){
    document.getElementById(id).scrollIntoView({ behavior: 'smooth' });
}

function goToLandingPage(){
    var targetPage = 'index.html';
    window.location.href = targetPage;
}

function goToGitLab(){
    var targetPage = "https://code.cs.umanitoba.ca/comp3350-winter2024/codelinguists-ao1-15";
    location.href = targetPage
}