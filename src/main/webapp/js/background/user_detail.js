
$(function(){
    var user_id = getUrlParam('id');
    getUserDetail(user_id);


});


/**
 *  功能描述：获取详情
 *  请求方式：GET
 *  请求地址：/api/user_manage/detail
 *  函数名称：getUserDetail
 *  参数：id:ID
 */

function getUserDetail(id){

    $.ajax({
        url:manage_path+'/api/user_manage/detail',
        type:'GET',
        dataType:'json',
        data:{
            id:id
        },
        beforeSend:function(){
            $.progressBar({message:'<p>正在努力加载数据...</p>',modal:true,canCance:true});
        },
        success:function(data){
            if(data.status == 0){
                var json = data.data;
                $('span[name=user_name]').html(json.userName);
                $('span[name=user_number]').html(json.userNum);
                $('span[name=phone_number]').html(json.phoneNumber);

                $('span[name=province_name]').html(json.province);
                $('span[name=city_name]').html(json.city);
                $('span[name=county_name]').html(json.county);

                $('span[name=user_email]').html(json.userEmail);
                $('span[name=post_code]').html(json.postCode);
                $('span[name=company_name]').html(json.companyName);
                $('span[name=register_ip]').html(json.registerIp);
                $('span[name=login_ip]').html(json.loginIp);
                $('span[name=start_time]').html(timestampFormat(json.startTime));
                $('span[name=end_time]').html(timestampFormat(json.endTime));
                $('span[name=ID_card]').html(json.identityNum);
                $('span[name=address]').html(json.address);

                $('span[name=hard_number]').html(json.hardNum);
                $('span[name=network_number]').html(json.networkNum);
                $('span[name=limit_money]').html(json.checkLimit);
                $('span[name=limit_number]').html(json.checkNum);

                var is_send = json.isSend;
                switch(is_send){
                    case 0:
                        is_send='不能'
                        break;
                    case 1:
                        is_send='可以'
                        break;
                }
                $('span[name=is_send_message]').html(is_send);

                var is_receive = json.isReceive;
                switch(is_receive){
                    case 0:
                        is_receive='不能'
                        break;
                    case 1:
                        is_receive='可以'
                        break;
                }
                $('span[name=is_receive_message]').html(is_receive);

                var is_receive_self = json.isReceiveSelf;
                switch(is_receive_self){
                    case 0:
                        is_receive_self='不能'
                        break;
                    case 1:
                        is_receive_self='可以'
                        break;
                }
                $('span[name=is_receive_self_message]').html(is_receive_self);


                $('span[name=send_city]').html(json.sendCity);
                $('span[name=receive_city]').html(json.receiveCity);


            }
        },
        complete:function(){
            $.progressBar().close();
        },
        error:function(XMLHttpRequest,textStatus,errorThrown){
            $.toast('服务器未响应，请稍候重试',5000);
        }
    });
}



