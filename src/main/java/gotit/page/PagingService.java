package gotit.page;

import java.util.List;

import gotit.model.Post;

public class PagingService {
    private static final PagingService INSTANCE = new PagingService();
    private final PageDAO dao = PageDAO.getInstance();

    private PagingService(){}

    public static PagingService getInstance(){ return INSTANCE; }
    
    public List<Post> getPostList() {
    	try {
            return null;
        } catch (Exception e) {
            return null;
        }
    }
}
