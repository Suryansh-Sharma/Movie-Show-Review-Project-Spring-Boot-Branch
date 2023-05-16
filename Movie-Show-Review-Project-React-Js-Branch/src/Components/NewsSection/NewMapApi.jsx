import {React,useState,useEffect} from 'react';
import axios from "axios";
import News from './News';
function NewMapApi() {
    const [data, setData] = useState();
	const apiKey = "81ea460426154f9f96bac3d8a542fe19";
    
	useEffect(() => {
	  axios
		.get(
		  `https://newsapi.org/v2/top-headlines?country=in&category=entertainment&apiKey=${apiKey}`
		)
		.then(response => {setData(response.data)})
		.catch((error) => console.log(error));
    localStorage.setItem("index",0);
	}, []);
  return (
    <div>
    <h1 className="h1 text-center text-light" id="pageHeaderTitle" >News  👋</h1>
    <div className="all__news">
      {data
        ? data.articles.map((news) => (
            <News data={news} key={news.url}/>
        ))
        : "Loading"}
    </div>
  </div>
  )
}

export default NewMapApi