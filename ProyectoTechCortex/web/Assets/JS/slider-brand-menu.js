$(document).ready(function(){
    if ($(".brand").length) {
        $(".brand").slick({
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
                        autoplaySpeed: 0,
                        speed: 4000,
                    },
                },
                {
                    breakpoint: 768,
                    settings: {
                        slidesToShow: 3,
                        slidesToScroll: 1,
                        autoplaySpeed: 0,
                        speed: 4000,
                    },
                },
                {
                    breakpoint: 480,
                    settings: {
                        slidesToShow: 2,
                        slidesToScroll: 1,
                        autoplaySpeed: 0,
                        speed: 4000,
                    },
                },
            ],
        });
    }

    if ($(".menu").length) {
        $(".menu").slick({
            dots: false,
            arrows: false,
            infinite: true,
            autoplay: true,
            fade: true,
            autoplaySpeed: 2000,
            speed: 2000,
            responsive: [
                {
                    breakpoint: 1024,
                    settings: {
                        slidesToShow: 1,
                        slidesToScroll: 1,
                        autoplaySpeed: 600,
                        speed: 2000,
                    },
                },
                {
                    breakpoint: 768,
                    settings: {
                        slidesToShow: 1,
                        slidesToScroll: 1,
                        autoplaySpeed: 1000,
                        speed: 2000,
                    },
                },
                {
                    breakpoint: 480,
                    settings: {
                        slidesToShow: 1,
                        slidesToScroll: 1,
                        autoplaySpeed: 1000,
                        speed: 2000,
                    },
                },
            ],
        });
    }
});
