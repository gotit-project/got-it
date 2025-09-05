package gotit.model;

public class Page {
	 private final int curPage;     // 1-base
	 private final int pageSize;
	 private final int totalCount;
	 private final int totalPage;
	 private final int offset;      // 0-base

	 public Page(int curPage, int pageSize, int totalCount) {
	     this.pageSize   = Math.max(1, pageSize);
	     this.totalCount = Math.max(0, totalCount);
	     this.totalPage  = Math.max(1, (int)Math.ceil(this.totalCount / (double)this.pageSize));
	
	     int p = curPage < 1 ? 1 : curPage;
	     if (p > this.totalPage) p = this.totalPage;
	     this.curPage = p;
	
	     int off = (this.curPage - 1) * this.pageSize;
	     this.offset = Math.max(0, off);
	 }

	 public int getCurPage()    { return curPage; }
	 public int getPageSize()   { return pageSize; }
	 public int getTotalCount() { return totalCount; }
	 public int getTotalPage()  { return totalPage; }
	 public int getOffset()     { return offset; }
	 
	}