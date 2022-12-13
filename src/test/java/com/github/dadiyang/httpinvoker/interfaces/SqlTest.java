package com.github.dadiyang.httpinvoker.interfaces;

/**
 * @author boyuxin
 * @description
 * @date 2022/12/14 1:42
 */
public class SqlTest {
    public static void main(String[] args) {
        //INSERT INTO `fosun_health`.`pc_family_doctor_user` ( `user_type`,  `create_time`, `update_time`, `current_status`, `login_name`, `username`, `password`, `upload_status`) VALUES ( 2, '2022-12-14 00:00:00', '2022-12-14 00:00:00', 1, '19921613937', '江雷', 'dcbf92d55d9bf19cc812389af76578d3', 0);


        String s = "INSERT INTO `fosun_health`.`pc_family_doctor_user` ( `user_type`,  `create_time`, `update_time`, `current_status`, `login_name`, `username`, `password`, `upload_status`) VALUES ( 2, '2022-12-14 00:00:00', '2022-12-14 00:00:00', 1, 'loginName', 'userName', 'dcbf92d55d9bf19cc812389af76578d3', 0);";
        String s1 = "INSERT INTO `fosun_health`.`t_consult_member_status` ( `doctor_id`, `doctor_role`, `status`, `room_status`, `created_at`, `created_by`, `updated_at`, `updated_by`, `usable_flag`) VALUES ( 'doctorId', 'DOCTOR_ASSISTANT', 'OFF_LINE', 'OPEN', '2022-12-14 00:00:00', 'SYS', '2022-12-14 00:00:00', 'SYS', 'USABLE');\n";
        String s2 = "INSERT INTO `fosun_health`.`t_consult_business_group_member` ( `doctor_id`, `doctor_name`, `doctor_role`, `group_id`, `created_at`, `created_by`, `updated_at`, `updated_by`, `usable_flag`) VALUES ( 'doctorId', 'doctorName', 'DOCTOR_ASSISTANT', 'group_56', '2022-12-14 00:00:00', 'SYS', '2022-12-14 00:00:00', 'SYS', 'USABLE');\n";
        String s3 = "INSERT INTO `fosun_health`.`pc_family_doctor_assistant_bind_doctor` ( `doctor_id`, `assistant_id`, `create_time`, `del`, `update_time`, `type`) VALUES (243360, doctorId, '2022-12-14 00:00:00', 0, '2022-12-14 00:00:00', 1);";
        for (int i = 200; i < 300; i++) {
            String doctorName = "助手"+ i;
            String doctor_id = 1183+i+"";
            String replace = s.replace("userName", doctorName).replace("loginName", i + "");
            String replace1 = s1.replace("doctorId", doctor_id);
            String replace2 = s2.replace("doctorName", doctorName).replace("doctorId", doctor_id);
            String replace3 = s3.replace("doctorId", doctor_id);
            System.out.println(replace);
//            System.out.println(replace2);
//            System.out.println(replace3);
        }
    }
}
