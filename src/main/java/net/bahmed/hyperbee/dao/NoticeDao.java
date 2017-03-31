package net.bahmed.hyperbee.dao;

import net.bahmed.hyperbee.domain.Notice;

import java.util.List;

/**
 * @author rumman
 * @since 11/22/16
 */
public interface NoticeDao {

    Notice saveOrUpdate(Notice notice);

    Notice findById(int noticeId);

    List<Notice> findAll();

    List<Notice> findLatestNotices(int range);

    void delete(Notice notice);

    void delete(int noticeId);

    List<Notice> getNoticeListByHiveId(int hiveId, int range);
}
