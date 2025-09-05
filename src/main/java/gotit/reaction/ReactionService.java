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

    // ======================
    // 좋아요
    // ======================
    public boolean likeToggle(Reaction reactionDto) {
        if (reactionDao.likeExists(reactionDto)) {
            return reactionDao.likeDelete(reactionDto);
        } else {
            return reactionDao.likeInsert(reactionDto);
        }
    }
    public boolean hasUserLiked(long postId, long userId) {
        return reactionDao.hasUserLiked(postId, userId);
    }

    // ======================
    // 스크랩
    // ======================
    public boolean scrapToggle(Reaction reactionDto) {
        if (reactionDao.scrapExists(reactionDto)) {
            return reactionDao.scrapDelete(reactionDto);
        } else {
            return reactionDao.scrapInsert(reactionDto);
        }
    }
    public boolean hasUserScrapped(long postId, long userId) {
        return reactionDao.hasUserScrapped(postId, userId);
    }
}
