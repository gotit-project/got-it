package gotit.reaction;

import gotit.reaction.ReactionDAO;
import gotit.reaction.ReactionService;
import gotit.model.Reaction;

public class ReactionService {
	private ReactionDAO reactionDao;
	
	private static final ReactionService instance = new ReactionService();
	
	private ReactionService() { 
		reactionDao = new ReactionDAO();
	}
	
	public static ReactionService getInstance() {
		return instance;
	}
	
	public boolean likeInsertS(Reaction reactionDto) {
		return reactionDao.likeInsert(reactionDto);
	}

    public boolean likeToggle(Reaction reactionDto) {
        if (reactionDao.likeExists(reactionDto)) {
            return reactionDao.likeDelete(reactionDto); // 이미 있으면 삭제
        } else {
            return reactionDao.likeInsert(reactionDto); // 없으면 추가
        }
    }

    public int likeCountByPostId(long postId) {
        return reactionDao.likeCountByPostId(postId);
    }
    
	public boolean scrapInsertS(Reaction reactionDto) {
		return reactionDao.likeInsert(reactionDto);
	}

    public boolean scrapToggle(Reaction reactionDto) {
        if (reactionDao.likeExists(reactionDto)) {
            return reactionDao.likeDelete(reactionDto); // 이미 있으면 삭제
        } else {
            return reactionDao.likeInsert(reactionDto); // 없으면 추가
        }
    }

    public int scrapCountByPostId(long postId) {
        return reactionDao.likeCountByPostId(postId);
    }
}
