package com.github.dadiyang.httpinvoker.interfaces;

import java.util.UUID;

/**
 * @author boyuxin
 * @description
 * @date 2022/12/14 1:42
 */
public class SqlTest {
    public static void main(String[] args) {
        //INSERT INTO `fosun_health`.`pc_family_doctor_user` ( `user_type`,  `create_time`, `update_time`, `current_status`, `login_name`, `username`, `password`, `upload_status`) VALUES ( 2, '2022-12-14 00:00:00', '2022-12-14 00:00:00', 1, '19921613937', '江雷', 'dcbf92d55d9bf19cc812389af76578d3', 0);


        String s = "INSERT INTO `fosun_health`.`pc_family_doctor_user` ( `user_type`,  `create_time`, `update_time`, `current_status`, `login_name`, `username`, `password`, `upload_status`) VALUES ( 2, '2022-12-14 00:00:00', '2022-12-14 00:00:00', 1, 'loginName', 'userName', 'dcbf92d55d9bf19cc812389af76578d3', 0);";
        String s1 = "INSERT INTO `fosun_health`.`t_consult_member_status` ( `doctor_id`, `doctor_role`, `status`, `room_status`, `created_at`, `created_by`, `updated_at`, `updated_by`, `usable_flag`) VALUES ( 'doctorId', 'DOCTOR_ASSISTANT', 'OFF_LINE', 'OPEN', '2022-12-14 00:00:00', '系统批量创建', '2022-12-14 00:00:00', '系统批量创建', 'USABLE');";
        String s2 = "INSERT INTO `fosun_health`.`t_consult_business_group_member` ( `doctor_id`, `doctor_name`, `doctor_role`, `group_id`, `created_at`, `created_by`, `updated_at`, `updated_by`, `usable_flag`) VALUES ( 'doctorId', 'doctorName', 'DOCTOR_ASSISTANT', 'group_61', '2022-12-14 00:00:00', '系统批量创建', '2022-12-14 00:00:00', '系统批量创建', 'USABLE');";
        String s3 = "INSERT INTO `fosun_health`.`pc_family_doctor_assistant_bind_doctor` ( `doctor_id`, `assistant_id`, `create_time`, `del`, `update_time`, `type`) VALUES (243360, doctorId, '2022-12-14 00:00:00', 0, '2022-12-14 00:00:00', 1);";
        String s4 = "INSERT INTO `consult_config`.`t_consult_concurrent_config` (`config_id`, `owner_id`, `owner_type`, `admissions_concurrent_num`, `business_type`, `status`, `master_code`, `consult_source`, `chat_type`, `created_at`, `created_by`, `updated_at`, `updated_by`, `usable_flag`) VALUES ( 'uuid', 'doctorId', 'DOCTOR', 1, 'QUICK_CONSULT', 'ENABLE', 'FOSUN_HEALTH', NULL, 'SINGLE', '2022-12-14 00:00:00', '系统批量创建', '2022-12-14 00:00:00', '系统批量创建', 'USABLE');";
        for (int i = 801; i < 1000; i++) {
            String uuid = UUID.randomUUID().toString().replaceAll("-", "").substring(0,16);
            String doctorName = "助手"+ i;
            String doctor_id = 1189+i+"";
            String replace = s.replace("userName", doctorName).replace("loginName", i + "");
            String replace1 = s1.replace("doctorId", doctor_id);
            String replace2 = s2.replace("doctorName", doctorName).replace("doctorId", doctor_id);
            String replace3 = s3.replace("doctorId", doctor_id);
            String replace4 = s4.replace("doctorId", doctor_id).replace("uuid", uuid);
//            System.out.println(replace);
//            System.out.println(replace1);
//            System.out.println(replace2);
//            System.out.println(replace3);
            System.out.println(replace4);
        }
    }
}
