package io.shuqi.graduation.domain.bo;

import java.util.List;

public class Pager<T> {
	private Long total;
	private Long totalPage;
	private Long current = 0L;
	private Long currentPage;
	//默认每页显示10条数据
	private int pagesize = 10;
	private List<T> datas;
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	public Long getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(Long totalPage) {
		this.totalPage = totalPage;
	}
	public Long getCurrent() {
		return current;
	}
	public void setCurrent(Long current) {
		this.current = current;
	}
	public Long getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Long currentPage) {
		this.currentPage = currentPage;
	}
	public int getPagesize() {
		return pagesize;
	}
	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}
	public List<T> getDatas() {
		return datas;
	}
	public void setDatas(List<T> datas) {
		this.datas = datas;
	}
	
	public Pager(Long currentPage, int pagesize) {
		super();
		this.currentPage = currentPage;
		this.pagesize = pagesize;
	}
	public Pager(Long total, Long totalPage, Long current, Long currentPage, int pagesize, List<T> datas) {
		super();
		this.total = total;
		this.totalPage = totalPage;
		this.current = current;
		this.currentPage = currentPage;
		this.pagesize = pagesize;
		this.datas = datas;
	}
	
	
}
