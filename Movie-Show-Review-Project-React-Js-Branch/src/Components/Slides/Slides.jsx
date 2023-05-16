import React, {useEffect} from 'react';
import "./Slides.css";
import Slider from "react-slick";
import LeftArrow from "../../icons/icons8-back-to-96.png";
import RightArrow from "../../icons/previcon.png";
import dataSource from "../Slides/DataSource";
import {useNavigate} from "react-router-dom";
import 'react-toastify/dist/ReactToastify.css';

const Slides = () => {
    useEffect(() => {
    }, [])
    let navigate = useNavigate();
    const SlickArrowLeft = ({currentSlide, slideCount, ...size}) => (
        <img src={LeftArrow} alt="prevArrow" {...size} />

    );

    const SlickArrowRight = ({currentSlide, slideCount, ...props}) => (
        <img src={RightArrow} alt="nextArrow" {...props} />
    );
    const settings = {
        prevArrow: <SlickArrowLeft/>,
        nextArrow: <SlickArrowRight/>,
        infinite: true,
        fade: true,
        cssEase: 'linear',
        slidesToShow: 1,
        slidesToScroll: 1,
        autoplay: true,
        speed: 2000,
        autoplaySpeed: 5500,
    };
    return (
        <div className="container-slider">
            <Slider  {...settings} className={"slides"}>
                <img src={dataSource[0].poster} className={"slider-image"}/>
                <img src={dataSource[1].poster} className={"slider-image"}/>
                <img src={dataSource[2].poster} className={"slider-image"}/>
                <img src={dataSource[3].poster} className={"slider-image"}/>
                <img src={dataSource[4].poster} className={"slider-image"}/>

            </Slider>
        </div>
    );
};

export default Slides;

