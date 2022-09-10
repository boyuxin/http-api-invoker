package com.github.dadiyang.httpinvoker.interfaces;

import java.io.File;

/**
 * @author boyuxin
 * @description
 * @date 2022/8/26 22:36
 */
public class test {
    public static void main(String[] args) {
        // 路径
        String path = "C:\\Users\\19695\\Desktop\\签名上传图片-提交开发(1)";
        File f = new File(path);

// 路径不存在
        if(!f.exists()){
            System.out.println(path + " not exists");
            return;
        }

        File result[] = f.listFiles();
        for(int i = 0; i<result.length; i++){
            File fs = result[i];
            if (fs.isFile()) {
                String name = fs.getName();
                String id = name.replace("qm.jpg", "");
                //UPDATE  `tp_zx_doctor` SET `e_sign` ='USABLE'  WHERE `id`=847
                //UPDATE `tp_doctor_prescription_authority` SET `keep_on_record` ='1' and  `w_medicine_prescription` ='1'  WHERE `doctor_id`=96136;
                //UPDATE `tp_zx_doctor` SET `is_dtp_doctor` = 1   WHERE `id`=212110;
                String sql = "UPDATE `tp_zx_doctor` SET `is_dtp_doctor` = 1   WHERE `id`=" + id +";";
//                System.out.println(",'"+id+"'");
                System.out.println(sql);
            }
        }

    }
}
