<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="io.shuqi.graduation.enumtype.EnumMimeType"%>
<%--100%框图片轮播，以及网站的公告、新闻也签 --%>
  			<div class="sepline"></div>
  			<div class="news_con">
  				<div>
  					<%--左边的图片轮播 --%>
  					<div class="playpic newspic">
  						<ul currentid="0">
  							<li class="current">
  								<img title="1"  src="${pageContext.request.contextPath }/commons/tempimg/pnews.jpg">
  								<div>
  									<div></div>
  									<span>
  										<a>这里就是要写的文章了额1</a>
  									</span>
  								</div>
  							</li>
  							<li>
  								<img alt="" width="100%" height="100%" src="${pageContext.request.contextPath }/commons/tempimg/pnews1.jpg">
  								<div>
  									<div></div>
  									<span>
  										<a>这里就是要写的文章了额2</a>
  									</span>
  								</div>
  							</li>
  							<li>
  								<img alt="" width="100%" height="100%" src="${pageContext.request.contextPath }/commons/tempimg/pnews2.jpg">
  								<div>
  									<div></div>
  									<span>
  										<a>这里就是要写的文章了额3aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa</a>
  									</span>
  								</div>
  							</li>
  							<ul>
	  							<li>1</li>
	  							<li>2</li>
	  							<li class="current">3</li>
  							</ul>
  						</ul>
  					</div>
  					<%--右边的页签 --%>
  					<div class="ctab newsctab">
						<div>
							<span class="current">最新公告</span>
							<span>最新新闻</span>
						</div>
						<div>
							<ul>
								<li >
									<div class="card mycard">
										<div class="content">
											<a href="#"> <img alt="" src="${pageContext.request.contextPath }/<%=EnumMimeType.EXECL.getPngImage() %>" style="width: 24px; height: 24px;" /> <span class="title">一个文件标题</span> <span>2015-11-23</span> <span>237</span> </a>
											<a href="#"> <img alt="" src="${pageContext.request.contextPath }/<%=EnumMimeType.EXECL.getPngImage() %>" style="width: 24px; height: 24px;" /> <span class="title">一个文件标题</span> <span>2015-11-23</span> <span>237</span> </a>
											<a href="#"> <img alt="" src="${pageContext.request.contextPath }/<%=EnumMimeType.EXECL.getPngImage() %>" style="width: 24px; height: 24px;" /> <span class="title">一个文件标题</span> <span>2015-11-23</span> <span>237</span> </a>
											<a href="#"> <img alt="" src="${pageContext.request.contextPath }/<%=EnumMimeType.EXECL.getPngImage() %>" style="width: 24px; height: 24px;" /> <span class="title">一个文件标题</span> <span>2015-11-23</span> <span>237</span> </a>
											<a href="#"> <img alt="" src="${pageContext.request.contextPath }/<%=EnumMimeType.EXECL.getPngImage() %>" style="width: 24px; height: 24px;" /> <span class="title">一个文件标题</span> <span>2015-11-23</span> <span>237</span> </a>
											<a href="#"> <img alt="" src="${pageContext.request.contextPath }/<%=EnumMimeType.EXECL.getPngImage() %>" style="width: 24px; height: 24px;" /> <span class="title">一个文件标题</span> <span>2015-11-23</span> <span>237</span> </a>
										</div>
									</div>
								</li>
								<li>
									<div class="card mycard">
										<div class="content">
											<a href="#"> <img alt="" src="${pageContext.request.contextPath }/<%=EnumMimeType.PDF.getPngImage() %>" style="width: 24px; height: 24px;" /> <span>一个文件标题</span> <span>2015-11-23</span> <span>237</span> </a>
											<a href="#"> <img alt="" src="${pageContext.request.contextPath }/<%=EnumMimeType.PDF.getPngImage() %>" style="width: 24px; height: 24px;" /> <span>一个文件标题</span> <span>2015-11-23</span> <span>237</span> </a>
											<a href="#"> <img alt="" src="${pageContext.request.contextPath }/<%=EnumMimeType.PDF.getPngImage() %>" style="width: 24px; height: 24px;" /> <span>一个文件标题</span> <span>2015-11-23</span> <span>237</span> </a>
											<a href="#"> <img alt="" src="${pageContext.request.contextPath }/<%=EnumMimeType.PDF.getPngImage() %>" style="width: 24px; height: 24px;" /> <span>一个文件标题</span> <span>2015-11-23</span> <span>237</span> </a>
											<a href="#"> <img alt="" src="${pageContext.request.contextPath }/<%=EnumMimeType.PDF.getPngImage() %>" style="width: 24px; height: 24px;" /> <span>一个文件标题</span> <span>2015-11-23</span> <span>237</span> </a>
											<a href="#"> <img alt="" src="${pageContext.request.contextPath }/<%=EnumMimeType.PDF.getPngImage() %>" style="width: 24px; height: 24px;" /> <span>一个文件标题</span> <span>2015-11-23</span> <span>237</span> </a>
											<a href="#"> <img alt="" src="${pageContext.request.contextPath }/<%=EnumMimeType.PDF.getPngImage() %>" style="width: 24px; height: 24px;" /> <span>一个文件标题</span> <span>2015-11-23</span> <span>237</span> </a>
										</div>
									</div>
								</li>
							</ul>
						</div>
					</div>
  				</div>
  			</div>
  			<div class="sepline"></div>
  			


<div class="ctab">
	<div>
		<span class="current">最新公告</span>
		<span>最新新闻</span>
	</div>
	<div>
		<ul>
			<li >
				<div class="card mycard">
					<div class="content">
						<a href="#"> <img alt="" src="${pageContext.request.contextPath }/<%=EnumMimeType.EXECL.getPngImage() %>" style="width: 24px; height: 24px;" /> <span class="title">一个文件标题</span> <span>2015-11-23</span> <span>237</span> </a>
						<a href="#"> <img alt="" src="${pageContext.request.contextPath }/<%=EnumMimeType.EXECL.getPngImage() %>" style="width: 24px; height: 24px;" /> <span class="title">一个文件标题</span> <span>2015-11-23</span> <span>237</span> </a>
						<a href="#"> <img alt="" src="${pageContext.request.contextPath }/<%=EnumMimeType.EXECL.getPngImage() %>" style="width: 24px; height: 24px;" /> <span class="title">一个文件标题</span> <span>2015-11-23</span> <span>237</span> </a>
						<a href="#"> <img alt="" src="${pageContext.request.contextPath }/<%=EnumMimeType.EXECL.getPngImage() %>" style="width: 24px; height: 24px;" /> <span class="title">一个文件标题</span> <span>2015-11-23</span> <span>237</span> </a>
						<a href="#"> <img alt="" src="${pageContext.request.contextPath }/<%=EnumMimeType.EXECL.getPngImage() %>" style="width: 24px; height: 24px;" /> <span class="title">一个文件标题</span> <span>2015-11-23</span> <span>237</span> </a>
						<a href="#"> <img alt="" src="${pageContext.request.contextPath }/<%=EnumMimeType.EXECL.getPngImage() %>" style="width: 24px; height: 24px;" /> <span class="title">一个文件标题</span> <span>2015-11-23</span> <span>237</span> </a>
					</div>
				</div>
			</li>
			<li>
				<div class="card mycard">
					<div class="content">
						<a href="#"> <img alt="" src="${pageContext.request.contextPath }/<%=EnumMimeType.PDF.getPngImage() %>" style="width: 24px; height: 24px;" /> <span>一个文件标题</span> <span>2015-11-23</span> <span>237</span> </a>
						<a href="#"> <img alt="" src="${pageContext.request.contextPath }/<%=EnumMimeType.PDF.getPngImage() %>" style="width: 24px; height: 24px;" /> <span>一个文件标题</span> <span>2015-11-23</span> <span>237</span> </a>
						<a href="#"> <img alt="" src="${pageContext.request.contextPath }/<%=EnumMimeType.PDF.getPngImage() %>" style="width: 24px; height: 24px;" /> <span>一个文件标题</span> <span>2015-11-23</span> <span>237</span> </a>
						<a href="#"> <img alt="" src="${pageContext.request.contextPath }/<%=EnumMimeType.PDF.getPngImage() %>" style="width: 24px; height: 24px;" /> <span>一个文件标题</span> <span>2015-11-23</span> <span>237</span> </a>
						<a href="#"> <img alt="" src="${pageContext.request.contextPath }/<%=EnumMimeType.PDF.getPngImage() %>" style="width: 24px; height: 24px;" /> <span>一个文件标题</span> <span>2015-11-23</span> <span>237</span> </a>
						<a href="#"> <img alt="" src="${pageContext.request.contextPath }/<%=EnumMimeType.PDF.getPngImage() %>" style="width: 24px; height: 24px;" /> <span>一个文件标题</span> <span>2015-11-23</span> <span>237</span> </a>
						<a href="#"> <img alt="" src="${pageContext.request.contextPath }/<%=EnumMimeType.PDF.getPngImage() %>" style="width: 24px; height: 24px;" /> <span>一个文件标题</span> <span>2015-11-23</span> <span>237</span> </a>
					</div>
				</div>
			</li>
		</ul>
	</div>
</div>
<div class="tab mytab1">
	<div>
		<span class="current"> <a href="#">最新课程</a> </span>
		<span> <a href="#">社区动态</a> </span>
		<span> <a href="#">最新资料下载</a> </span>
	</div>
	<div>
		<div class="current">
			<div class="card mycard">
				<div class="content">
					<a href="#"> <img alt="" src="${pageContext.request.contextPath }/<%=EnumMimeType.EXECL.getPngImage() %>" style="width: 24px; height: 24px;" /> <span>一个文件标题</span> <span>2015-11-23</span> <span>237</span> </a>
					<a href="#"> <img alt="" src="${pageContext.request.contextPath }/<%=EnumMimeType.POWERPOINT.getPngImage() %>" style="width: 24px; height: 24px;" /> <span>一个文件标题</span> <span>2015-11-23</span> <span>237</span> </a>
					<a href="#"> <img alt="" src="${pageContext.request.contextPath }/<%=EnumMimeType.PDF.getPngImage() %>" style="width: 24px; height: 24px;" /> <span>一个文件标题</span> <span>2015-11-23</span> <span>237</span> </a>
					<a href="#"> <img alt="" src="${pageContext.request.contextPath }/<%=EnumMimeType.PDF.getPngImage() %>" style="width: 24px; height: 24px;" /> <span>一个文件标题</span> <span>2015-11-23</span> <span>237</span> </a>
					<a href="#"> <img alt="" src="${pageContext.request.contextPath }/<%=EnumMimeType.PDF.getPngImage() %>" style="width: 24px; height: 24px;" /> <span>一个文件标题</span> <span>2015-11-23</span> <span>237</span> </a>
					<a href="#"> <img alt="" src="${pageContext.request.contextPath }/<%=EnumMimeType.PDF.getPngImage() %>" style="width: 24px; height: 24px;" /> <span>一个文件标题</span> <span>2015-11-23</span> <span>237</span> </a>
					<a href="#"> <img alt="" src="${pageContext.request.contextPath }/<%=EnumMimeType.PDF.getPngImage() %>" style="width: 24px; height: 24px;" /> <span>一个文件标题</span> <span>2015-11-23</span> <span>237</span> </a>
					<a href="#"> <img alt="" src="${pageContext.request.contextPath }/<%=EnumMimeType.PDF.getPngImage() %>" style="width: 24px; height: 24px;" /> <span>一个文件标题</span> <span>2015-11-23</span> <span>237</span> </a>
				</div>
			</div>
		</div>
		<div>
			<table class="bm_table">
				<tr>
					<th>
						编号
					</th>
					<th>
						标题
					</th>
					<th>
						日期
					</th>
					<th>
						发布人
					</th>
				</tr>
				<tr>
					<td>
						1
					</td>
					<td>
						aaqqqqdassssss
					</td>
					<td>
						2015-12-23
					</td>
					<td>
						赵钱孙
					</td>
				</tr>
				<tr>
					<td>
						1
					</td>
					<td>
						aaqqqqdassssss
					</td>
					<td>
						2015-12-23
					</td>
					<td>
						赵钱孙
					</td>
				</tr>
				<tr>
					<td>
						1
					</td>
					<td>
						aaqqqqdassssss
					</td>
					<td>
						2015-12-23
					</td>
					<td>
						赵钱孙
					</td>
				</tr>
				<tr>
					<td>
						1
					</td>
					<td>
						aaqqqqdassssss
					</td>
					<td>
						2015-12-23
					</td>
					<td>
						赵钱孙
					</td>
				</tr>
				<tr>
					<td>
						1
					</td>
					<td>
						aaqqqqdassssss
					</td>
					<td>
						2015-12-23
					</td>
					<td>
						赵钱孙
					</td>
				</tr>
				<tr>
					<td>
						1
					</td>
					<td>
						aaqqqqdassssss
					</td>
					<td>
						2015-12-23
					</td>
					<td>
						赵钱孙
					</td>
				</tr>
				<tr>
					<td>
						1
					</td>
					<td>
						aaqqqqdassssss
					</td>
					<td>
						2015-12-23
					</td>
					<td>
						赵钱孙
					</td>
				</tr>
				<tr>
					<td>
						1
					</td>
					<td>
						aaqqqqdassssss
					</td>
					<td>
						2015-12-23
					</td>
					<td>
						赵钱孙
					</td>
				</tr>
			</table>
		</div>
		<div>
			tab3_content
		</div>
	</div>
</div>

<div class="card mycard">
	<div class="title">
		<img alt="" src="${pageContext.request.contextPath }/<%=EnumMimeType.FOLDER.getPngImage() %>">
		<span>我是卡片的标题</span>
		<div class="bclear"></div>
	</div>
	<div class="content">
		<a href="#"> <img alt="" src="${pageContext.request.contextPath }/<%=EnumMimeType.EXECL.getPngImage() %>" style="width: 24px; height: 24px;" /> <span>一个文件标题</span> <span>2015-11-23</span> <span>237</span> </a>
		<a href="#"> <img alt="" src="${pageContext.request.contextPath }/<%=EnumMimeType.POWERPOINT.getPngImage() %>" style="width: 24px; height: 24px;" /> <span>一个文件标题</span> <span>2015-11-23</span> <span>237</span> </a>
		<a href="#"> <img alt="" src="${pageContext.request.contextPath }/<%=EnumMimeType.PDF.getPngImage() %>" style="width: 24px; height: 24px;" /> <span>一个文件标题</span> <span>2015-11-23</span> <span>237</span> </a>
	</div>
</div>
<div class="static_card mycard">
	<div class="title">
		<img alt="" src="${pageContext.request.contextPath }/<%=EnumMimeType.FOLDER.getPngImage() %>">
		<span>我是卡片的标题</span>
		<div class="bclear"></div>
	</div>
	<div class="content">

	</div>
</div>