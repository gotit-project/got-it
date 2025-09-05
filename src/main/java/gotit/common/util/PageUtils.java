package gotit.common.util;

import gotit.model.Page;

public final class PageUtils {
	 private PageUtils() {}
	 public static Page build(int requestedPage, int pageSize, int totalCount) {
	     return new Page(requestedPage, pageSize, totalCount);
	 }
}