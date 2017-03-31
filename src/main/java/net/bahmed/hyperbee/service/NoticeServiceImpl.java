package net.bahmed.hyperbee.service;

import net.bahmed.hyperbee.dao.NoticeDao;
import net.bahmed.hyperbee.domain.Notice;
import net.bahmed.hyperbee.web.helper.NoticeHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author rumman
 * @since 11/22/16
 */
@Service
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    private NoticeDao noticeDao;

    @Autowired
    private NoticeHelper noticeHelper;

    @Override
    @Transactional
    public void saveNotice(Notice notice) {
        noticeDao.saveOrUpdate(notice);
        noticeHelper.persistInSession();
    }

    @Override
    public Notice findNoticeById(int noticeId) {
        return noticeDao.findById(noticeId);
    }

    @Override
    public List<Notice> findAllNotice() {
        return noticeDao.findAll();
    }

    @Override
    public List<Notice> findLatestNotices(int range) {
        return noticeDao.findLatestNotices(range);
    }

    @Override
    @Transactional
    public void deleteNotice(Notice notice) {
        noticeDao.delete(notice);
        noticeHelper.persistInSession();
    }

    @Override
    @Transactional
    public void delete(int noticeId) {
        noticeDao.delete(noticeId);
        noticeHelper.persistInSession();
    }

    @Override
    public List<Notice> getNoticeListByHiveId(int hiveId, int range) {
        return noticeDao.getNoticeListByHiveId(hiveId, range);
    }
}
