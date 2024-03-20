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