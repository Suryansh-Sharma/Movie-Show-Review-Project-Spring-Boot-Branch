import {React,useState,useEffect} from 'react'
import "./NewsSection.scss";
function News({data}) {
	const [Flag, setFlag] = useState({
		one:false,
		two:true,
		third:false,
		fourth:false
	});
	useEffect(() => {
		let index=(localStorage.getItem("index"));
		if(index==="0"){
			setFlag({...Flag,one:true,two:false,third:false,fourth:false});
			index="1";
		}else if(index==="1"){
			setFlag({...Flag,one:false,two:true,third:false,fourth:false});
			index="2";
		}else if(index==="2"){
			setFlag({...Flag,one:false,two:false,third:true,fourth:false});
			index="3";
		}else if(index==="3"){
			setFlag({...Flag,one:false,two:false,third:false,fourth:true});
			index="0";
		}
		localStorage.setItem("index",index);
	}, [])
	

	return (
		<div className={"News"}>
			<section className="light">
				<div className="container py-2">
					<div className="h1 text-center text-dark" id="pageHeaderTitle"/>
					
				{
				(Flag.one===true) ? 
						<article className="postcard light blue">
						<a className="postcard__img_link">
							<img className="postcard__img" src={data.urlToImage} alt="Image Title" />
						</a>
						<div className="postcard__text t-dark">
							<h1 className="postcard__title blue"><a href={data.url}  target="_blank">{data.title}</a></h1>
							<div className="postcard__subtitle small">
								<time >
									<i className="fas fa-calendar-alt mr-2">{data.publishedAt}</i>
								</time>
							</div>
							<div className="postcard__bar"></div>
							<div className="postcard__preview-txt">{data.description} {data.content}</div>

						</div>
					</article>
				:null
				}
				{
				(Flag.two===true) ? 
					<article className="postcard light red">
						<a className="postcard__img_link" href="#">
							<img className="postcard__img" src={data.urlToImage} alt="Image Title" />
						</a>
						<div className="postcard__text t-dark">
							<h1 className="postcard__title red"><a href={data.url}  target="_blank">{data.title}</a></h1>
							<div className="postcard__subtitle small">
								<time >
									<i className="fas fa-calendar-alt mr-2">{data.publishedAt}</i>
								</time>
							</div>
							<div className="postcard__bar"></div>
							<div className="postcard__preview-txt">{data.description}</div>
						</div>
					</article>
									:null
								}
									{
				(Flag.third===true) ? 
					<article className="postcard light green">
						<a className="postcard__img_link" href="#">
							<img className="postcard__img" src={data.urlToImage} />
						</a>
						<div className="postcard__text t-dark">
							<h1 className="postcard__title green"><a href={data.url}  target="_blank">{data.title}</a></h1>
							<div className="postcard__subtitle small">
								<time >
									<i className="fas fa-calendar-alt mr-2">{data.publishedAt}</i>
								</time>
							</div>
							<div className="postcard__bar"></div>
							<div className="postcard__preview-txt">{data.description}</div>
						</div>
					</article>
														:null
													}
														{
				(Flag.fourth===true) ? 
					<article className="postcard light yellow">
						<a className="postcard__img_link" href="#">
							<img className="postcard__img" src={data.urlToImage} />
						</a>
						<div className="postcard__text t-dark">
							<h1 className="postcard__title yellow"><a href={data.url}  target="_blank">{data.title}</a></h1>
							<div className="postcard__subtitle small">
								<time >
									<i className="fas fa-calendar-alt mr-2">{data.publishedAt}</i>
								</time>
							</div>
							<div className="postcard__bar"></div>
							<div className="postcard__preview-txt">{data.description}</div>
	
						</div>
					</article>
														:null
													}
				</div>
			</section>
		</div>
	)
}

export default News