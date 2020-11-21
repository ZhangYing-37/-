var index = parent.layer.getFrameIndex(window.name);

function closeThis(){
    parent.layer.close(index);
};

layui.use('form', function(){
    var form = layui.form
    form.on('submit(formPD)', function (data) {
        console.log(data.field);
        $.ajax({
            url:"updateSubjectPD",
            type:"post",
            async:false,
            data: JSON.stringify(data.field),
            contentType:"application/json",
            dataType:"json",
            success:function (data) {
            }
        })
        window.parent.location.reload();
        parent.layer.close(index);
        return false;
    });
})

function optionUp(i,j) {
    var $tr = $("#option"+i);
    if (i!=1){
        layui.use('layer', function(){
            var layer = layui.layer;
            layer.load();
            setTimeout(function(){
                layer.closeAll('loading');
            }, 1000);
        });

        $tr.fadeOut().fadeIn();
        $tr.prev().before($tr);
        var index = i-1;
        $tr.find("#o_name"+i).attr("name","o_name"+index);
        $tr.find("#o_name"+i).attr("id","o_name"+index);
        $tr.find("#isTrue"+i).attr("value",index);
        $tr.find("#isTrue"+i).attr("id","isTrue"+index);
        $tr.find("#optionUp"+i).attr("onclick","optionUp("+index+","+j+")");
        $tr.find("#optionUp"+i).attr("id","optionUp"+index);
        $tr.find("#optionDown"+i).attr("onclick","optionDown("+index+","+j+")");
        $tr.find("#optionDown"+i).attr("id","optionDown"+index);
        $tr.next().find("#o_name"+index).attr("name","o_name"+i);
        $tr.next().find("#o_name"+index).attr("id","o_name"+i);
        $tr.next().find("#isTrue"+index).attr("value",i);
        $tr.next().find("#isTrue"+index).attr("id","isTrue"+i);
        $tr.next().find("#optionUp"+index).attr("onclick","optionUp("+i+","+j+")");
        $tr.next().find("#optionUp"+index).attr("id","optionUp"+i);
        $tr.next().find("#optionDown"+index).attr("onclick","optionDown("+i+","+j+")");
        $tr.next().find("#optionDown"+index).attr("id","optionDown"+i);
        $tr.next().attr("id","option"+i);
        $tr.attr("id","option"+index);
    }else {
        layui.use('layer', function(){
            var layer = layui.layer;
            layer.msg('已经到顶了！');
        });
    }
}

function optionDown(i,j) {
    var $tr = $("#option"+i);
    if (i!=j){
        layui.use('layer', function(){
            var layer = layui.layer;
            layer.load();
            setTimeout(function(){
                layer.closeAll('loading');
            }, 1000);
        });

        $tr.fadeOut().fadeIn();
        $tr.next().after($tr);
        var index = i+1;
        $tr.find("#o_name"+i).attr("name","o_name"+index);
        $tr.find("#o_name"+i).attr("id","o_name"+index);
        $tr.find("#isTrue"+i).attr("value",index);
        $tr.find("#isTrue"+i).attr("id","isTrue"+index);
        $tr.find("#optionUp"+i).attr("onclick","optionUp("+index+","+j+")");
        $tr.find("#optionUp"+i).attr("id","optionUp"+index);
        $tr.find("#optionDown"+i).attr("onclick","optionDown("+index+","+j+")");
        $tr.find("#optionDown"+i).attr("id","optionDown"+index);
        $tr.prev().find("#o_name"+index).attr("name","o_name"+i);
        $tr.prev().find("#o_name"+index).attr("id","o_name"+i);
        $tr.prev().find("#isTrue"+index).attr("value",i);
        $tr.prev().find("#isTrue"+index).attr("id","isTrue"+i);
        $tr.prev().find("#optionUp"+index).attr("onclick","optionUp("+i+","+j+")");
        $tr.prev().find("#optionUp"+index).attr("id","optionUp"+i);
        $tr.prev().find("#optionDown"+index).attr("onclick","optionDown("+i+","+j+")");
        $tr.prev().find("#optionDown"+index).attr("id","optionDown"+i);
        $tr.prev().attr("id","option"+i);
        $tr.attr("id","option"+index);
    }else {
        layui.use('layer', function(){
            var layer = layui.layer;
            layer.msg('已经到底了！');
        });
    }
}