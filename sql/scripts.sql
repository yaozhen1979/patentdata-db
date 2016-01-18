CREATE TABLE app_cur_assignee
(
   app_id                 varchar(50)    NOT NULL,
   item                   integer        NOT NULL,
   current_assignee_name  varchar(50)    NOT NULL,
   create_date            date           NOT NULL,
   last_upd_date          date           NOT NULL
);

ALTER TABLE app_cur_assignee
   ADD CONSTRAINT app_cur_assignee_pkey
   PRIMARY KEY (app_id, item);

CREATE TABLE app_data
(
   app_id         varchar(50)    NOT NULL,
   country        varchar(2)     NOT NULL,
   app_no         varchar(50)    NOT NULL,
   app_date       date           NOT NULL,
   app_lang       varchar(2),
   create_date    date           NOT NULL,
   last_upd_date  date           NOT NULL
);

ALTER TABLE app_data
   ADD CONSTRAINT app_data_pkey
   PRIMARY KEY (app_id);

CREATE TABLE pat_cls_cpc
(
   pat_id           varchar(50)    NOT NULL,
   source_id        varchar(50)    NOT NULL,
   item             integer        NOT NULL,
   cpc_text         varchar(30)    NOT NULL,
   symbol_position  varchar(1),
   cls_version      varchar(50),
   cls_value        varchar(1),
   action_date      date,
   gen_office       varchar(2),
   cls_status       varchar(1),
   cls_data_source  varchar(1),
   schema_ori_code  varchar(10),
   main_flag        integer        NOT NULL,
   create_date      date           NOT NULL,
   last_upd_date    date           NOT NULL
);

ALTER TABLE pat_cls_cpc
   ADD CONSTRAINT pat_cls_cpc_pkey
   PRIMARY KEY (pat_id, source_id, item);

CREATE TABLE pat_cls_cset
(
   pat_id           varchar(50)    NOT NULL,
   source_id        varchar(50)    NOT NULL,
   group_no         varchar(10)    NOT NULL,
   item             varchar(50)    NOT NULL,
   cpc_text         varchar(30)    NOT NULL,
   symbol_position  varchar(1),
   cls_version      varchar(50),
   cls_value        varchar(1),
   action_date      date,
   gen_office       varchar(2),
   cls_status       varchar(1),
   cls_data_source  varchar(1),
   schema_ori_code  varchar(10)
);

ALTER TABLE pat_cls_cset
   ADD CONSTRAINT pat_cls_cset_pkey
   PRIMARY KEY (pat_id, source_id, group_no, item);

CREATE TABLE pat_cls_di
(
   pat_id         varchar(50)    NOT NULL,
   source_id      varchar(50)    NOT NULL,
   item           integer        NOT NULL,
   di_text        varchar(50)    NOT NULL,
   cls_version    varchar(50),
   main_flag      integer        NOT NULL,
   create_date    date           NOT NULL,
   last_upd_date  date           NOT NULL
);

ALTER TABLE pat_cls_di
   ADD CONSTRAINT pat_cls_di_pkey
   PRIMARY KEY (pat_id, source_id, item);

CREATE TABLE pat_cls_dterm
(
   pat_id         varchar(50)    NOT NULL,
   source_id      varchar(50)    NOT NULL,
   item           integer        NOT NULL,
   dterm_text     varchar(50)    NOT NULL,
   cls_version    varchar(50),
   main_flag      integer        NOT NULL,
   create_date    date           NOT NULL,
   last_upd_date  date           NOT NULL
);

ALTER TABLE pat_cls_dterm
   ADD CONSTRAINT pat_cls_dterm_pkey
   PRIMARY KEY (pat_id, source_id, item);

CREATE TABLE pat_cls_ecla
(
   pat_id         varchar(50)    NOT NULL,
   source_id      varchar(50)    NOT NULL,
   item           integer        NOT NULL,
   ecla_text      varchar(50)    NOT NULL,
   cls_version    varchar(50),
   main_flag      integer        NOT NULL,
   create_date    date           NOT NULL,
   last_upd_date  date           NOT NULL
);

ALTER TABLE pat_cls_ecla
   ADD CONSTRAINT pat_cls_ecla_pkey
   PRIMARY KEY (pat_id, source_id, item);

CREATE TABLE pat_cls_fi
(
   pat_id         varchar(50)    NOT NULL,
   source_id      varchar(50)    NOT NULL,
   item           integer        NOT NULL,
   fi_text        varchar(50)    NOT NULL,
   cls_version    varchar(50),
   main_flag      integer        NOT NULL,
   create_date    date           NOT NULL,
   last_upd_date  date           NOT NULL
);

ALTER TABLE pat_cls_fi
   ADD CONSTRAINT pat_cls_fi_pkey
   PRIMARY KEY (pat_id, source_id, item);

CREATE TABLE pat_cls_filed_of_search
(
   pat_id         varchar(50)    NOT NULL,
   source_id      varchar(50)    NOT NULL,
   item           integer        NOT NULL,
   class_type     varchar(10)    NOT NULL,
   class_text     varchar(30)    NOT NULL,
   create_date    date           NOT NULL,
   last_upd_date  date           NOT NULL
);

ALTER TABLE pat_cls_filed_of_search
   ADD CONSTRAINT pat_cls_filed_of_search_pkey
   PRIMARY KEY (pat_id, source_id, item);

CREATE TABLE pat_cls_fterm
(
   pat_id         varchar(50)    NOT NULL,
   source_id      varchar(50)    NOT NULL,
   item           integer        NOT NULL,
   fterm_text     varchar(50)    NOT NULL,
   cls_version    varchar(50),
   main_flag      integer        NOT NULL,
   create_date    date           NOT NULL,
   last_upd_date  date           NOT NULL
);

ALTER TABLE pat_cls_fterm
   ADD CONSTRAINT pat_cls_fterm_pkey
   PRIMARY KEY (pat_id, source_id, item);

CREATE TABLE pat_cls_ipc
(
   pat_id           varchar(50)    NOT NULL,
   source_id        varchar(50)    NOT NULL,
   item             integer        NOT NULL,
   ipc_text         varchar(30)    NOT NULL,
   symbol_position  varchar(1),
   cls_version      varchar(50),
   cls_level        varchar(1),
   cls_value        varchar(1),
   action_date      date,
   gen_office       varchar(2),
   cls_status       varchar(1),
   cls_data_source  varchar(1),
   main_flag        integer        NOT NULL,
   create_date      date           NOT NULL,
   last_upd_date    date           NOT NULL
);

ALTER TABLE pat_cls_ipc
   ADD CONSTRAINT pat_cls_ipc_pkey
   PRIMARY KEY (pat_id, source_id, item);

CREATE TABLE pat_cls_loc
(
   pat_id         varchar(50)    NOT NULL,
   source_id      varchar(50)    NOT NULL,
   item           integer        NOT NULL,
   loc_text       varchar(50)    NOT NULL,
   cls_version    varchar(50),
   main_flag      integer        NOT NULL,
   create_date    date           NOT NULL,
   last_upd_date  date           NOT NULL
);

ALTER TABLE pat_cls_loc
   ADD CONSTRAINT pat_cls_loc_pkey
   PRIMARY KEY (pat_id, source_id, item);

CREATE TABLE pat_cls_uspc
(
   pat_id         varchar(50)    NOT NULL,
   source_id      varchar(50)    NOT NULL,
   item           integer        NOT NULL,
   uspc_text      varchar(9)     NOT NULL,
   main_flag      integer        NOT NULL,
   create_date    date           NOT NULL,
   last_upd_date  date           NOT NULL
);

ALTER TABLE pat_cls_uspc
   ADD CONSTRAINT pat_cls_uspc_pkey
   PRIMARY KEY (pat_id, source_id, item);

CREATE TABLE pat_data
(
   pat_id                      varchar(50)    NOT NULL,
   country                     varchar(2)     NOT NULL,
   stat                        integer        NOT NULL,
   raw_doc_no                  varchar(50)    NOT NULL,
   doc_no                      varchar(50)    NOT NULL,
   doc_date                    date           NOT NULL,
   kind_code                   varchar(2)     NOT NULL,
   pat_type                    integer        NOT NULL,
   raw_app_no                  varchar(50)    NOT NULL,
   app_id                      varchar(50)    NOT NULL,
   registration_no             varchar(50),
   registration_date           date,
   family_id                   varchar(30),
   default_source_id           varchar(50)    NOT NULL,
   ori_lang                    varchar(2)     NOT NULL,
   full_text_flag              integer        NOT NULL,
   crypto_flag                 integer        NOT NULL,
   withdraw_flag               integer        NOT NULL,
   delete_flag                 integer        NOT NULL,
   index_criteria              varchar(50)    NOT NULL,
   correspondence_person_id    varchar(50),
   size_of_calims              integer,
   size_of_clips               integer,
   size_of_figures             integer,
   grant_years                 integer,
   grant_extension_days        integer,
   page_no_of_claim            integer,
   page_no_of_desc             integer,
   page_no_of_figure           integer,
   page_no_of_first_img        integer,
   totla_pages                 integer,
   first_img_flag              integer,
   truncate_flag               integer,
   gazette_no                  varchar(50),
   gazette_public_date         date,
   public_lang                 varchar(50),
   designation_of_states_json  varchar(50),
   create_date                 date           NOT NULL,
   last_upd_date               date           NOT NULL
);

ALTER TABLE pat_data
   ADD CONSTRAINT pat_data_pkey
   PRIMARY KEY (pat_id);

CREATE TABLE pat_data_mul_lang
(
   pat_id         varchar(50)    NOT NULL,
   source_id      varchar(50)    NOT NULL,
   lang           varchar(2)     NOT NULL,
   title          text,
   brief          text,
   description    text,
   claims         text,
   create_date    date           NOT NULL,
   last_upd_date  date           NOT NULL
);

ALTER TABLE pat_data_mul_lang
   ADD CONSTRAINT pat_data_mul_lang_pkey
   PRIMARY KEY (pat_id, source_id, lang);

CREATE TABLE pat_event_record
(
   pat_id      varchar(50)     NOT NULL,
   source_id   varchar(50)     NOT NULL,
   record_id   varchar(50)     NOT NULL,
   event_desc  varchar(50)     NOT NULL,
   event_date  date            NOT NULL,
   remark      varchar(100)    NOT NULL
);

ALTER TABLE pat_event_record
   ADD CONSTRAINT pat_event_record_pkey
   PRIMARY KEY (record_id);

CREATE TABLE pat_event_record_amend_log
(
   pat_id       varchar(50)    NOT NULL,
   source_id    varchar(50)    NOT NULL,
   record_id    varchar(50)    NOT NULL,
   log_id       integer        NOT NULL,
   col_name     varchar(50)    NOT NULL,
   cur_val      varchar(50)    NOT NULL,
   amend_val    varchar(50)    NOT NULL,
   create_date  date           NOT NULL
);

ALTER TABLE pat_event_record_amend_log
   ADD CONSTRAINT pat_event_record_amend_log_pkey
   PRIMARY KEY (log_id);

CREATE TABLE pat_person_agent
(
   pat_id         varchar(50)    NOT NULL,
   source_id      varchar(50)    NOT NULL,
   item           varchar(10)    NOT NULL,
   person_id      varchar(50)    NOT NULL,
   rep_type       integer        NOT NULL,
   create_date    date           NOT NULL,
   last_upd_date  date           NOT NULL
);

ALTER TABLE pat_person_agent
   ADD CONSTRAINT pat_person_agent_pkey
   PRIMARY KEY (pat_id, source_id, item);

CREATE TABLE pat_person_applicant
(
   pat_id         varchar(50)    NOT NULL,
   source_id      varchar(50)    NOT NULL,
   item           integer        NOT NULL,
   person_id      varchar(50)    NOT NULL,
   app_type       integer,
   designation    integer,
   auth_type      integer,
   create_date    date           NOT NULL,
   last_upd_date  date           NOT NULL
);

ALTER TABLE pat_person_applicant
   ADD CONSTRAINT pat_person_applicant_pkey
   PRIMARY KEY (pat_id, source_id, item);

CREATE TABLE pat_person_assignee
(
   pat_id         varchar(50)    NOT NULL,
   source_id      varchar(50)    NOT NULL,
   item           integer        NOT NULL,
   person_id      varchar(50)    NOT NULL,
   create_date    date           NOT NULL,
   last_upd_date  date           NOT NULL
);

ALTER TABLE pat_person_assignee
   ADD CONSTRAINT pat_person_assignee_pkey
   PRIMARY KEY (pat_id, source_id, item);

CREATE TABLE pat_person_examiner
(
   pat_id         varchar(50)    NOT NULL,
   source_id      varchar(50)    NOT NULL,
   item           integer        NOT NULL,
   person_id      varchar(50)    NOT NULL,
   examiner_type  char(1)        NOT NULL,
   create_date    date           NOT NULL,
   last_upd_date  date           NOT NULL
);

ALTER TABLE pat_person_examiner
   ADD CONSTRAINT pat_person_examiner_pkey
   PRIMARY KEY (pat_id, source_id, item);

CREATE TABLE pat_person_inventor
(
   pat_id         varchar(50)    NOT NULL,
   source_id      varchar(50)    NOT NULL,
   item           integer        NOT NULL,
   person_id      varchar(50)    NOT NULL,
   designation    integer,
   auth_category  integer,
   create_date    date           NOT NULL,
   last_upd_date  date           NOT NULL
);

ALTER TABLE pat_person_inventor
   ADD CONSTRAINT pat_person_inventor_pkey
   PRIMARY KEY (pat_id, source_id, item);

CREATE TABLE pat_pto_cn
(
   raw_id         varchar(50)    NOT NULL,
   pat_id         varchar(50)    NOT NULL,
   raw_json       varchar(50)    NOT NULL,
   create_date    varchar(50)    NOT NULL,
   last_upd_date  date           NOT NULL
);

ALTER TABLE pat_pto_cn
   ADD CONSTRAINT pat_pto_cn_pkey
   PRIMARY KEY (raw_id);

CREATE TABLE pat_pto_docdb
(
   raw_id              varchar(50)    NOT NULL,
   pat_id              varchar(50)    NOT NULL,
   lastest_flag        integer        NOT NULL,
   raw_json            varchar(50)    NOT NULL,
   family_member_json  varchar(50),
   create_date         date           NOT NULL,
   last_upd_date       date           NOT NULL
);

ALTER TABLE pat_pto_docdb
   ADD CONSTRAINT pat_pto_docdb_pkey
   PRIMARY KEY (raw_id);

CREATE TABLE pat_pto_ep
(
   raw_id         varchar(50)    NOT NULL,
   pat_id         varchar(50)    NOT NULL,
   raw_json       varchar(50)    NOT NULL,
   create_date    varchar(50)    NOT NULL,
   last_upd_date  date           NOT NULL
);

ALTER TABLE pat_pto_ep
   ADD CONSTRAINT pat_pto_ep_pkey
   PRIMARY KEY (raw_id);

CREATE TABLE pat_pto_jp
(
   raw_id         varchar(50)    NOT NULL,
   pat_id         varchar(50)    NOT NULL,
   raw_json       varchar(50)    NOT NULL,
   create_date    date           NOT NULL,
   last_upd_date  date           NOT NULL
);

ALTER TABLE pat_pto_jp
   ADD CONSTRAINT pat_pto_jp_pkey
   PRIMARY KEY (raw_id);

CREATE TABLE pat_pto_kr
(
   raw_id         varchar(50)    NOT NULL,
   pat_id         varchar(50)    NOT NULL,
   raw_json       varchar(50)    NOT NULL,
   create_date    date           NOT NULL,
   last_upd_date  date           NOT NULL
);

ALTER TABLE pat_pto_kr
   ADD CONSTRAINT pat_pto_kr_pkey
   PRIMARY KEY (raw_id);

CREATE TABLE pat_pto_tw
(
   raw_id         varchar(50)    NOT NULL,
   pat_id         varchar(50)    NOT NULL,
   raw_json       varchar(50)    NOT NULL,
   create_date    date           NOT NULL,
   last_upd_date  date           NOT NULL
);

ALTER TABLE pat_pto_tw
   ADD CONSTRAINT pat_pto_tw_pkey
   PRIMARY KEY (raw_id);

CREATE TABLE pat_pto_us
(
   raw_id         varchar(50)    NOT NULL,
   pat_id         varchar(50)    NOT NULL,
   raw_json       varchar(50)    NOT NULL,
   create_date    date           NOT NULL,
   last_upd_date  date           NOT NULL
);

ALTER TABLE pat_pto_us
   ADD CONSTRAINT pat_pto_us_pkey
   PRIMARY KEY (raw_id);

CREATE TABLE pat_pto_wo
(
   raw_id         varchar(50)    NOT NULL,
   pat_id         varchar(50)    NOT NULL,
   lastest_flag   integer        NOT NULL,
   raw_json       varchar(50)    NOT NULL,
   create_date    date           NOT NULL,
   last_upd_date  date           NOT NULL
);

ALTER TABLE pat_pto_wo
   ADD CONSTRAINT pat_pto_wo_pkey
   PRIMARY KEY (raw_id);

CREATE TABLE pat_ref_cited
(
   pat_id         varchar(50)    NOT NULL,
   source_id      varchar(50)    NOT NULL,
   item           integer        NOT NULL,
   doc_no         varchar(50)    NOT NULL,
   country        varchar(10),
   raw_doc_no     varchar(50),
   kind_code      varchar(2),
   inventor_name  varchar(50),
   doc_date       date,
   app_date       date,
   cited_by_type  integer        NOT NULL,
   cited_pat_id   varchar(50),
   create_date    date           NOT NULL,
   last_upd_date  date           NOT NULL
);

ALTER TABLE pat_ref_cited
   ADD CONSTRAINT pat_ref_cited_pkey
   PRIMARY KEY (pat_id, source_id, item);

CREATE TABLE pat_ref_cited_cls
(
   pat_id         varchar(50)    NOT NULL,
   source_id      varchar(50)    NOT NULL,
   cited_item     integer        NOT NULL,
   item           integer        NOT NULL,
   class_type     varchar(10)    NOT NULL,
   class_text     varchar(30)    NOT NULL,
   create_date    date           NOT NULL,
   last_upd_date  date           NOT NULL
);

ALTER TABLE pat_ref_cited_cls
   ADD CONSTRAINT pat_ref_cited_cls_pkey
   PRIMARY KEY (pat_id, source_id, cited_item, item);

CREATE TABLE pat_ref_cited_npl
(
   pat_id         varchar(50)      NOT NULL,
   source_id      varchar(50)      NOT NULL,
   item           integer          NOT NULL,
   npl_content    varchar(1000)    NOT NULL,
   cited_by_type  integer          NOT NULL,
   create_date    date             NOT NULL,
   last_upd_date  date             NOT NULL
);

ALTER TABLE pat_ref_cited_npl
   ADD CONSTRAINT pat_ref_cited_npl_pkey
   PRIMARY KEY (pat_id, source_id, item);

CREATE TABLE pat_ref_cited_npl_cls
(
   pat_id         varchar(50)    NOT NULL,
   source_id      varchar(50)    NOT NULL,
   npl_item       integer        NOT NULL,
   item           integer        NOT NULL,
   class_type     varchar(10)    NOT NULL,
   class_text     varchar(30)    NOT NULL,
   create_date    date           NOT NULL,
   last_upd_date  date           NOT NULL
);

ALTER TABLE pat_ref_cited_npl_cls
   ADD CONSTRAINT pat_ref_cited_npl_cls_pkey
   PRIMARY KEY (pat_id, source_id, npl_item, item);

CREATE TABLE pat_ref_pct
(
   pat_id               varchar(50)    NOT NULL,
   source_id            varchar(50)    NOT NULL,
   raw_app_no           varchar(50)    NOT NULL,
   app_no               varchar(50)    NOT NULL,
   pct_app_id           varchar(50),
   app_date             date           NOT NULL,
   pct_371c124_date     date,
   article_22_39_date   date,
   pct_371c12_date      varchar(10),
   create_date          date           NOT NULL,
   last_upd_date        date           NOT NULL,
   raw_public_no        varchar(50)    NOT NULL,
   public_no            varchar(50)    NOT NULL,
   public_pat_id        varchar(50),
   public_date          date,
   public_gazette_no    varchar(50),
   public_gazette_date  date
);

ALTER TABLE pat_ref_pct
   ADD CONSTRAINT pat_ref_pct_pkey
   PRIMARY KEY (pat_id, source_id);

CREATE TABLE pat_ref_priority
(
   pat_id         varchar(50)    NOT NULL,
   source_id      varchar(50)    NOT NULL,
   item           integer        NOT NULL,
   app_no         varchar(50)    NOT NULL,
   app_id         varchar(50),
   pri_type       integer,
   country        varchar(2),
   raw_app_no     varchar(50),
   app_date       date,
   create_date    date           NOT NULL,
   last_upd_date  date           NOT NULL
);

ALTER TABLE pat_ref_priority
   ADD CONSTRAINT pat_ref_priority_pkey
   PRIMARY KEY (pat_id, source_id, item);

CREATE TABLE pat_ref_related_parent
(
   pat_id           varchar(50)    NOT NULL,
   source_id        varchar(50)    NOT NULL,
   item             integer        NOT NULL,
   related_type     integer        NOT NULL,
   raw_app_no       varchar(50),
   app_no           varchar(50),
   app_date         date,
   parent_app_id    varchar(50),
   raw_doc_no       varchar(50),
   doc_no           varchar(50),
   parent_pat_id    varchar(50),
   doc_date         date,
   kind_code        varchar(2),
   pat_status       varchar(50),
   raw_pct_app_no   varchar(50),
   pct_app_no       varchar(50),
   pct_app_date     date,
   pct_app_id       varchar(50),
   create_date      date           NOT NULL,
   last_upd_date    date           NOT NULL,
   inventor_name    varchar(50),
   correction_code  varchar(50),
   gazettle_no      varchar(50)
);

ALTER TABLE pat_ref_related_parent
   ADD CONSTRAINT pat_ref_related_parent_pkey
   PRIMARY KEY (pat_id, source_id, item);

CREATE TABLE pat_ref_related_parent_child
(
   pat_id         varchar(50)    NOT NULL,
   source_id      varchar(50)    NOT NULL,
   parent_item    integer        NOT NULL,
   item           integer        NOT NULL,
   raw_app_no     varchar(50)    NOT NULL,
   app_no         varchar(50)    NOT NULL,
   child_app_id   varchar(50),
   create_date    date           NOT NULL,
   last_upd_date  date           NOT NULL
);

ALTER TABLE pat_ref_related_parent_child
   ADD CONSTRAINT pat_ref_related_parent_child_pkey
   PRIMARY KEY (pat_id, source_id, parent_item, item);

CREATE TABLE person_data
(
   person_id      varchar(50)    NOT NULL,
   person_type    integer        NOT NULL,
   name           varchar(50),
   address        varchar(50),
   city           varchar(50),
   state          varchar(50),
   country        varchar(50)    NOT NULL,
   lang           varchar(2)     NOT NULL,
   create_date    date           NOT NULL,
   last_upd_date  date           NOT NULL
);

ALTER TABLE person_data
   ADD CONSTRAINT person_data_pkey
   PRIMARY KEY (person_id);

CREATE TABLE source_data
(
   source_id  varchar(50)    NOT NULL,
   remark     varchar(50)    NOT NULL
);

ALTER TABLE source_data
   ADD CONSTRAINT source_data_pkey
   PRIMARY KEY (source_id);


ALTER TABLE app_cur_assignee
  ADD CONSTRAINT app_cur_assignee_app_id_fkey FOREIGN KEY (app_id)
  REFERENCES app_data (app_id)
  ON UPDATE NO ACTION
  ON DELETE NO ACTION;

ALTER TABLE pat_cls_cpc
  ADD CONSTRAINT pat_cls_cpc_pat_id_fkey FOREIGN KEY (pat_id)
  REFERENCES pat_data (pat_id)
  ON UPDATE NO ACTION
  ON DELETE NO ACTION;

ALTER TABLE pat_cls_cpc
  ADD CONSTRAINT pat_cls_cpc_source_id_fkey FOREIGN KEY (source_id)
  REFERENCES source_data (source_id)
  ON UPDATE NO ACTION
  ON DELETE NO ACTION;

ALTER TABLE pat_cls_cset
  ADD CONSTRAINT pat_cls_cset_pat_id_fkey FOREIGN KEY (pat_id)
  REFERENCES pat_data (pat_id)
  ON UPDATE NO ACTION
  ON DELETE NO ACTION;

ALTER TABLE pat_cls_cset
  ADD CONSTRAINT pat_cls_cset_source_id_fkey FOREIGN KEY (source_id)
  REFERENCES source_data (source_id)
  ON UPDATE NO ACTION
  ON DELETE NO ACTION;

ALTER TABLE pat_cls_di
  ADD CONSTRAINT pat_cls_di_pat_id_fkey FOREIGN KEY (pat_id)
  REFERENCES pat_data (pat_id)
  ON UPDATE NO ACTION
  ON DELETE NO ACTION;

ALTER TABLE pat_cls_di
  ADD CONSTRAINT pat_cls_di_source_id_fkey FOREIGN KEY (source_id)
  REFERENCES source_data (source_id)
  ON UPDATE NO ACTION
  ON DELETE NO ACTION;

ALTER TABLE pat_cls_dterm
  ADD CONSTRAINT pat_cls_dterm_pat_id_fkey FOREIGN KEY (pat_id)
  REFERENCES pat_data (pat_id)
  ON UPDATE NO ACTION
  ON DELETE NO ACTION;

ALTER TABLE pat_cls_dterm
  ADD CONSTRAINT pat_cls_dterm_source_id_fkey FOREIGN KEY (source_id)
  REFERENCES source_data (source_id)
  ON UPDATE NO ACTION
  ON DELETE NO ACTION;

ALTER TABLE pat_cls_ecla
  ADD CONSTRAINT pat_cls_ecla_pat_id_fkey FOREIGN KEY (pat_id)
  REFERENCES pat_data (pat_id)
  ON UPDATE NO ACTION
  ON DELETE NO ACTION;

ALTER TABLE pat_cls_ecla
  ADD CONSTRAINT pat_cls_ecla_source_id_fkey FOREIGN KEY (source_id)
  REFERENCES source_data (source_id)
  ON UPDATE NO ACTION
  ON DELETE NO ACTION;

ALTER TABLE pat_cls_fi
  ADD CONSTRAINT pat_cls_fi_pat_id_fkey FOREIGN KEY (pat_id)
  REFERENCES pat_data (pat_id)
  ON UPDATE NO ACTION
  ON DELETE NO ACTION;

ALTER TABLE pat_cls_fi
  ADD CONSTRAINT pat_cls_fi_source_id_fkey FOREIGN KEY (source_id)
  REFERENCES source_data (source_id)
  ON UPDATE NO ACTION
  ON DELETE NO ACTION;

ALTER TABLE pat_cls_filed_of_search
  ADD CONSTRAINT pat_cls_filed_of_search_pat_id_fkey FOREIGN KEY (pat_id)
  REFERENCES pat_data (pat_id)
  ON UPDATE NO ACTION
  ON DELETE NO ACTION;

ALTER TABLE pat_cls_filed_of_search
  ADD CONSTRAINT pat_cls_filed_of_search_source_id_fkey FOREIGN KEY (source_id)
  REFERENCES source_data (source_id)
  ON UPDATE NO ACTION
  ON DELETE NO ACTION;

ALTER TABLE pat_cls_fterm
  ADD CONSTRAINT pat_cls_fterm_pat_id_fkey FOREIGN KEY (pat_id)
  REFERENCES pat_data (pat_id)
  ON UPDATE NO ACTION
  ON DELETE NO ACTION;

ALTER TABLE pat_cls_fterm
  ADD CONSTRAINT pat_cls_fterm_source_id_fkey FOREIGN KEY (source_id)
  REFERENCES source_data (source_id)
  ON UPDATE NO ACTION
  ON DELETE NO ACTION;

ALTER TABLE pat_cls_ipc
  ADD CONSTRAINT pat_cls_ipc_pat_id_fkey FOREIGN KEY (pat_id)
  REFERENCES pat_data (pat_id)
  ON UPDATE NO ACTION
  ON DELETE NO ACTION;

ALTER TABLE pat_cls_ipc
  ADD CONSTRAINT pat_cls_ipc_source_id_fkey FOREIGN KEY (source_id)
  REFERENCES source_data (source_id)
  ON UPDATE NO ACTION
  ON DELETE NO ACTION;

ALTER TABLE pat_cls_loc
  ADD CONSTRAINT pat_cls_loc_pat_id_fkey FOREIGN KEY (pat_id)
  REFERENCES pat_data (pat_id)
  ON UPDATE NO ACTION
  ON DELETE NO ACTION;

ALTER TABLE pat_cls_loc
  ADD CONSTRAINT pat_cls_loc_source_id_fkey FOREIGN KEY (source_id)
  REFERENCES source_data (source_id)
  ON UPDATE NO ACTION
  ON DELETE NO ACTION;

ALTER TABLE pat_cls_uspc
  ADD CONSTRAINT pat_cls_uspc_pat_id_fkey FOREIGN KEY (pat_id)
  REFERENCES pat_data (pat_id)
  ON UPDATE NO ACTION
  ON DELETE NO ACTION;

ALTER TABLE pat_cls_uspc
  ADD CONSTRAINT pat_cls_uspc_source_id_fkey FOREIGN KEY (source_id)
  REFERENCES source_data (source_id)
  ON UPDATE NO ACTION
  ON DELETE NO ACTION;

ALTER TABLE pat_data_mul_lang
  ADD CONSTRAINT pat_data_mul_lang_pat_id_fkey FOREIGN KEY (pat_id)
  REFERENCES pat_data (pat_id)
  ON UPDATE NO ACTION
  ON DELETE NO ACTION;

ALTER TABLE pat_data_mul_lang
  ADD CONSTRAINT pat_data_mul_lang_source_id_fkey FOREIGN KEY (source_id)
  REFERENCES source_data (source_id)
  ON UPDATE NO ACTION
  ON DELETE NO ACTION;

ALTER TABLE pat_event_record
  ADD CONSTRAINT pat_event_record_pat_id_fkey FOREIGN KEY (pat_id)
  REFERENCES pat_data (pat_id)
  ON UPDATE NO ACTION
  ON DELETE NO ACTION;

ALTER TABLE pat_event_record
  ADD CONSTRAINT pat_event_record_source_id_fkey FOREIGN KEY (source_id)
  REFERENCES source_data (source_id)
  ON UPDATE NO ACTION
  ON DELETE NO ACTION;

ALTER TABLE pat_event_record_amend_log
  ADD CONSTRAINT pat_event_record_amend_log_record_id_fkey FOREIGN KEY (record_id)
  REFERENCES pat_event_record (record_id)
  ON UPDATE NO ACTION
  ON DELETE NO ACTION;

ALTER TABLE pat_person_agent
  ADD CONSTRAINT pat_person_agent_pat_id_fkey FOREIGN KEY (pat_id)
  REFERENCES pat_data (pat_id)
  ON UPDATE NO ACTION
  ON DELETE NO ACTION;

ALTER TABLE pat_person_agent
  ADD CONSTRAINT pat_person_agent_person_id_fkey FOREIGN KEY (person_id)
  REFERENCES person_data (person_id)
  ON UPDATE NO ACTION
  ON DELETE NO ACTION;

ALTER TABLE pat_person_agent
  ADD CONSTRAINT pat_person_agent_source_id_fkey FOREIGN KEY (source_id)
  REFERENCES source_data (source_id)
  ON UPDATE NO ACTION
  ON DELETE NO ACTION;

ALTER TABLE pat_person_applicant
  ADD CONSTRAINT pat_person_applicant_pat_id_fkey FOREIGN KEY (pat_id)
  REFERENCES pat_data (pat_id)
  ON UPDATE NO ACTION
  ON DELETE NO ACTION;

ALTER TABLE pat_person_applicant
  ADD CONSTRAINT pat_person_applicant_person_id_fkey FOREIGN KEY (person_id)
  REFERENCES person_data (person_id)
  ON UPDATE NO ACTION
  ON DELETE NO ACTION;

ALTER TABLE pat_person_applicant
  ADD CONSTRAINT pat_person_applicant_source_id_fkey FOREIGN KEY (source_id)
  REFERENCES source_data (source_id)
  ON UPDATE NO ACTION
  ON DELETE NO ACTION;

ALTER TABLE pat_person_assignee
  ADD CONSTRAINT pat_person_assignee_pat_id_fkey FOREIGN KEY (pat_id)
  REFERENCES pat_data (pat_id)
  ON UPDATE NO ACTION
  ON DELETE NO ACTION;

ALTER TABLE pat_person_assignee
  ADD CONSTRAINT pat_person_assignee_person_id_fkey FOREIGN KEY (person_id)
  REFERENCES person_data (person_id)
  ON UPDATE NO ACTION
  ON DELETE NO ACTION;

ALTER TABLE pat_person_assignee
  ADD CONSTRAINT pat_person_assignee_source_id_fkey FOREIGN KEY (source_id)
  REFERENCES source_data (source_id)
  ON UPDATE NO ACTION
  ON DELETE NO ACTION;

ALTER TABLE pat_person_examiner
  ADD CONSTRAINT pat_person_examiner_pat_id_fkey FOREIGN KEY (pat_id)
  REFERENCES pat_data (pat_id)
  ON UPDATE NO ACTION
  ON DELETE NO ACTION;

ALTER TABLE pat_person_examiner
  ADD CONSTRAINT pat_person_examiner_person_id_fkey FOREIGN KEY (person_id)
  REFERENCES person_data (person_id)
  ON UPDATE NO ACTION
  ON DELETE NO ACTION;

ALTER TABLE pat_person_examiner
  ADD CONSTRAINT pat_person_examiner_source_id_fkey FOREIGN KEY (source_id)
  REFERENCES source_data (source_id)
  ON UPDATE NO ACTION
  ON DELETE NO ACTION;

ALTER TABLE pat_person_inventor
  ADD CONSTRAINT pat_person_inventor_pat_id_fkey FOREIGN KEY (pat_id)
  REFERENCES pat_data (pat_id)
  ON UPDATE NO ACTION
  ON DELETE NO ACTION;

ALTER TABLE pat_person_inventor
  ADD CONSTRAINT pat_person_inventor_person_id_fkey FOREIGN KEY (person_id)
  REFERENCES person_data (person_id)
  ON UPDATE NO ACTION
  ON DELETE NO ACTION;

ALTER TABLE pat_person_inventor
  ADD CONSTRAINT pat_person_inventor_source_id_fkey FOREIGN KEY (source_id)
  REFERENCES source_data (source_id)
  ON UPDATE NO ACTION
  ON DELETE NO ACTION;

ALTER TABLE pat_pto_cn
  ADD CONSTRAINT pat_pto_cn_pat_id_fkey FOREIGN KEY (pat_id)
  REFERENCES pat_data (pat_id)
  ON UPDATE NO ACTION
  ON DELETE NO ACTION;

ALTER TABLE pat_pto_docdb
  ADD CONSTRAINT pat_pto_docdb_pat_id_fkey FOREIGN KEY (pat_id)
  REFERENCES pat_data (pat_id)
  ON UPDATE NO ACTION
  ON DELETE NO ACTION;

ALTER TABLE pat_pto_ep
  ADD CONSTRAINT pat_pto_ep_pat_id_fkey FOREIGN KEY (pat_id)
  REFERENCES pat_data (pat_id)
  ON UPDATE NO ACTION
  ON DELETE NO ACTION;

ALTER TABLE pat_pto_jp
  ADD CONSTRAINT pat_pto_jp_pat_id_fkey FOREIGN KEY (pat_id)
  REFERENCES pat_data (pat_id)
  ON UPDATE NO ACTION
  ON DELETE NO ACTION;

ALTER TABLE pat_pto_kr
  ADD CONSTRAINT pat_pto_kr_pat_id_fkey FOREIGN KEY (pat_id)
  REFERENCES pat_data (pat_id)
  ON UPDATE NO ACTION
  ON DELETE NO ACTION;

ALTER TABLE pat_pto_tw
  ADD CONSTRAINT pat_pto_tw_pat_id_fkey FOREIGN KEY (pat_id)
  REFERENCES pat_data (pat_id)
  ON UPDATE NO ACTION
  ON DELETE NO ACTION;

ALTER TABLE pat_pto_us
  ADD CONSTRAINT pat_pto_us_pat_id_fkey FOREIGN KEY (pat_id)
  REFERENCES pat_data (pat_id)
  ON UPDATE NO ACTION
  ON DELETE NO ACTION;

ALTER TABLE pat_pto_wo
  ADD CONSTRAINT pat_pto_wo_pat_id_fkey FOREIGN KEY (pat_id)
  REFERENCES pat_data (pat_id)
  ON UPDATE NO ACTION
  ON DELETE NO ACTION;

ALTER TABLE pat_ref_cited
  ADD CONSTRAINT pat_ref_cited_pat_id_fkey FOREIGN KEY (pat_id)
  REFERENCES pat_data (pat_id)
  ON UPDATE NO ACTION
  ON DELETE NO ACTION;

ALTER TABLE pat_ref_cited
  ADD CONSTRAINT pat_ref_cited_source_id_fkey FOREIGN KEY (source_id)
  REFERENCES source_data (source_id)
  ON UPDATE NO ACTION
  ON DELETE NO ACTION;

ALTER TABLE pat_ref_cited_cls
  ADD CONSTRAINT pat_ref_cited_cls_pat_id_fkey FOREIGN KEY (pat_id,source_id,cited_item)
  REFERENCES pat_ref_cited (pat_id,source_id,item)
  ON UPDATE NO ACTION
  ON DELETE NO ACTION;

ALTER TABLE pat_ref_cited_npl
  ADD CONSTRAINT pat_ref_cited_npl_pat_id_fkey FOREIGN KEY (pat_id)
  REFERENCES pat_data (pat_id)
  ON UPDATE NO ACTION
  ON DELETE NO ACTION;

ALTER TABLE pat_ref_cited_npl
  ADD CONSTRAINT pat_ref_cited_npl_source_id_fkey FOREIGN KEY (source_id)
  REFERENCES source_data (source_id)
  ON UPDATE NO ACTION
  ON DELETE NO ACTION;

ALTER TABLE pat_ref_cited_npl_cls
  ADD CONSTRAINT pat_ref_cited_npl_cls_pat_id_fkey FOREIGN KEY (pat_id,source_id,npl_item)
  REFERENCES pat_ref_cited_npl (pat_id,source_id,item)
  ON UPDATE NO ACTION
  ON DELETE NO ACTION;

ALTER TABLE pat_ref_pct
  ADD CONSTRAINT pat_ref_pct_pct_app_id_fkey FOREIGN KEY (pct_app_id)
  REFERENCES app_data (app_id)
  ON UPDATE NO ACTION
  ON DELETE NO ACTION;

ALTER TABLE pat_ref_pct
  ADD CONSTRAINT pat_ref_pct_pat_id_fkey FOREIGN KEY (pat_id)
  REFERENCES pat_data (pat_id)
  ON UPDATE NO ACTION
  ON DELETE NO ACTION;

ALTER TABLE pat_ref_pct
  ADD CONSTRAINT pat_ref_pct_source_id_fkey FOREIGN KEY (source_id)
  REFERENCES source_data (source_id)
  ON UPDATE NO ACTION
  ON DELETE NO ACTION;

ALTER TABLE pat_ref_priority
  ADD CONSTRAINT pat_ref_priority_app_id_fkey FOREIGN KEY (app_id)
  REFERENCES app_data (app_id)
  ON UPDATE NO ACTION
  ON DELETE NO ACTION;

ALTER TABLE pat_ref_priority
  ADD CONSTRAINT pat_ref_priority_pat_id_fkey FOREIGN KEY (pat_id)
  REFERENCES pat_data (pat_id)
  ON UPDATE NO ACTION
  ON DELETE NO ACTION;

ALTER TABLE pat_ref_priority
  ADD CONSTRAINT pat_ref_priority_source_id_fkey FOREIGN KEY (source_id)
  REFERENCES source_data (source_id)
  ON UPDATE NO ACTION
  ON DELETE NO ACTION;

ALTER TABLE pat_ref_related_parent
  ADD CONSTRAINT pat_ref_related_parent_pat_id_fkey FOREIGN KEY (pat_id)
  REFERENCES pat_data (pat_id)
  ON UPDATE NO ACTION
  ON DELETE NO ACTION;

ALTER TABLE pat_ref_related_parent
  ADD CONSTRAINT pat_ref_related_parent_source_id_fkey FOREIGN KEY (source_id)
  REFERENCES source_data (source_id)
  ON UPDATE NO ACTION
  ON DELETE NO ACTION;

ALTER TABLE pat_ref_related_parent_child
  ADD CONSTRAINT pat_ref_related_parent_child_child_app_id_fkey FOREIGN KEY (child_app_id)
  REFERENCES app_data (app_id)
  ON UPDATE NO ACTION
  ON DELETE NO ACTION;


COMMIT;
