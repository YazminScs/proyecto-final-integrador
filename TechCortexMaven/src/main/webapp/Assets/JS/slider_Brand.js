$(document).ready(function () {
    $(".slick-carousel").slick({
        dots: false,
        arrows: false,
        infinite: true,
        autoplay: true,
        autoplaySpeed: 0,
        speed: 4000,
        cssEase: 'linear',
        slidesToShow: 5,
        slidesToScroll: 1, 
        responsive: [
            {
                breakpoint: 1024,
                settings: {
                    slidesToShow: 4,
                    slidesToScroll: 1,
                    autoplaySpeed: 600,
                    speed: 2000,
                },
            },
            {
                breakpoint: 768,
                settings: {
                    slidesToShow: 3,
                    slidesToScroll: 1,
                    autoplaySpeed: 1000,
                    speed: 2000,
                },
            },
            {
                breakpoint: 480,
                settings: {
                    slidesToShow: 2,
                    slidesToScroll: 1,
                    autoplaySpeed: 1000,
                    speed: 2000,
                },
            },
        ],
    });
    $('.lazy').slick({
        lazyLoad: 'ondemand',
        slidesToShow: 3,
        slidesToScroll: 1
    });
});

