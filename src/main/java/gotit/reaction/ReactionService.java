package gotit.reaction;

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

   	/* ==========================
   	 * 좋아요 토글 
   	 * ========================== */
    public boolean likeToggle(Reaction reactionDto) {
        if (reactionDao.likeExists(reactionDto)) {
            return reactionDao.likeDelete(reactionDto);
        } else {
            return reactionDao.likeInsert(reactionDto);
        }
    }

   	/* ==========================
   	 * 사용자가 해당 게시글에 좋아요
   	 * 눌렀는지 확인
   	 * ========================== */
    public boolean hasUserLiked(long postId, long userId) {
        return reactionDao.hasUserLiked(postId, userId);
    }

   	/* ==========================
   	 * 스크랩 토글 
   	 * ========================== */
    public boolean scrapToggle(Reaction reactionDto) {
        if (reactionDao.scrapExists(reactionDto)) {
            return reactionDao.scrapDelete(reactionDto);
        } else {
            return reactionDao.scrapInsert(reactionDto);
        }
    }

   	/* ==========================
   	 * 사용자가 해당 게시글에 스크랩 
   	 * 눌렀는지 확인
   	 * ========================== */
    public boolean hasUserScrapped(long postId, long userId) {
        return reactionDao.hasUserScrapped(postId, userId);
    }
}
