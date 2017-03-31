package net.bahmed.hyperbee.service;

import net.bahmed.hyperbee.domain.Notice;

import java.util.List;

/**
 * @author rumman
 * @since 11/22/16
 */
public interface NoticeService {

    void saveNotice(Notice notice);

    Notice findNoticeById(int noticeId);

    List<Notice> findAllNotice();

    List<Notice> findLatestNotices(int range);

    void deleteNotice(Notice notice);

    void delete(int noticeId);

    List<Notice> getNoticeListByHiveId(int hiveId, int range);
}
