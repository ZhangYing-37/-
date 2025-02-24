var index = parent.layer.getFrameIndex(window.name);

function closeThis(){
    parent.layer.close(index);
};

layui.use('form', function(){
    var form = layui.form
    form.on('submit(formDX1)', function (data) {
        console.log(data.field);
        $.ajax({
            url:"updateSubjectDX1",
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

function addTR(i,j) {

    layui.use('layer', function(){
        var layer = layui.layer;
        layer.load();
        setTimeout(function(){
            layer.closeAll('loading');
        }, 1000);
    });
    var $tr = $("#option"+i);
    var $trCopy = $tr.clone(true);
    var max = j+1;
    var nextIndex = i+1;
    for (var k=j;k>i;k--){
        var $tr0 = $("#option"+k);
        var index = k+1;
        $tr0.find("#o_name"+k).attr("name","o_name"+index);
        $tr0.find("#o_name"+k).attr("id","o_name"+index);
        $tr0.find("#optionAdd"+k).attr("onclick","addTR("+index+","+max+")");
        $tr0.find("#optionAdd"+k).attr("id","optionAdd"+index);
        $tr0.find("#optionRemove"+k).attr("onclick","removeTR("+index+","+max+")");
        $tr0.find("#optionRemove"+k).attr("id","optionRemove"+index);
        $tr0.find("#isTrue"+k).attr("value",index);
        $tr0.find("#isTrue"+k).attr("id","isTrue"+index);
        $tr0.find("#optionUp"+k).attr("onclick","optionUp("+index+","+max+")");
        $tr0.find("#optionUp"+k).attr("id","optionUp"+index);
        $tr0.find("#optionDown"+k).attr("onclick","optionDown("+index+","+max+")");
        $tr0.find("#optionDown"+k).attr("id","optionDown"+index);
        $tr0.attr("id","option"+index);
    }

    for (var k=1;k<=i;k++){
        var $tr0 = $("#option"+k);
        $tr0.find("#optionAdd"+k).attr("onclick","addTR("+k+","+max+")");
        $tr0.find("#optionRemove"+k).attr("onclick","removeTR("+k+","+max+")");
        $tr0.find("#optionUp"+k).attr("onclick","optionUp("+k+","+max+")");
        $tr0.find("#optionUp"+k).attr("id","optionUp"+k);
        $tr0.find("#optionDown"+k).attr("onclick","optionDown("+k+","+max+")");
        $tr0.find("#optionDown"+k).attr("id","optionDown"+k);
    }

    $tr.after($trCopy);
    $tr.next().find("#o_name"+i).attr("name","o_name"+nextIndex);
    $tr.next().find("#o_name"+i).attr("value","选项"+max);
    $tr.next().find("#o_name"+i).attr("id","o_name"+nextIndex);
    $tr.next().find("#optionAdd"+i).attr("onclick","addTR("+nextIndex+","+max+")");
    $tr.next().find("#optionAdd"+i).attr("id","optionAdd"+nextIndex);
    $tr.next().find("#optionRemove"+i).attr("onclick","removeTR("+nextIndex+","+max+")");
    $tr.next().find("#optionRemove"+i).attr("id","optionRemove"+nextIndex);
    $tr.next().find("#isTrue"+i).attr("value",nextIndex);
    $tr.next().find("#isTrue"+i).attr("id","isTrue"+nextIndex);
    $tr.next().attr("id","option"+nextIndex);
    $tr.next().find("#optionUp"+i).attr("onclick","optionUp("+nextIndex+","+max+")");
    $tr.next().find("#optionUp"+i).attr("id","optionUp"+nextIndex);
    $tr.next().find("#optionDown"+i).attr("onclick","optionDown("+nextIndex+","+max+")");
    $tr.next().find("#optionDown"+i).attr("id","optionDown"+nextIndex);

    layui.use('form', function(){
        var form = layui.form;
        form.render();
    });
}

function removeTR(i,j) {
    if(j>2){
        layui.use('layer', function(){
            var layer = layui.layer;
            layer.load();
            setTimeout(function(){
                layer.closeAll('loading');
            }, 1000);
        });
        var $tr = $("#option"+i);
        var max = j-1;
        var preIndex = i-1;
        $tr.remove();
        for (var l=i+1;l<=j;l++){
            var $tr0 = $("#option"+l);
            var index = l-1;
            $tr0.find("#o_name"+l).attr("name","o_name"+index);
            $tr0.find("#o_name"+l).attr("id","o_name"+index);
            $tr0.find("#optionAdd"+l).attr("onclick","addTR("+index+","+max+")");
            $tr0.find("#optionAdd"+l).attr("id","optionAdd"+index);
            $tr0.find("#optionRemove"+l).attr("onclick","removeTR("+index+","+max+")");
            $tr0.find("#optionRemove"+l).attr("id","optionRemove"+index);
            $tr0.find("#isTrue"+l).attr("value",index);
            $tr0.find("#isTrue"+l).attr("id","isTrue"+index);
            $tr0.find("#optionUp"+l).attr("onclick","optionUp("+index+","+max+")");
            $tr0.find("#optionUp"+l).attr("id","optionUp"+index);
            $tr0.find("#optionDown"+l).attr("onclick","optionDown("+index+","+max+")");
            $tr0.find("#optionDown"+l).attr("id","optionDown"+index);
            $tr0.attr("id","option"+index);
        }

        for (var k=1;k<i;k++){
            var $tr0 = $("#option"+k);
            $tr0.find("#optionAdd"+k).attr("onclick","addTR("+k+","+max+")");
            $tr0.find("#optionRemove"+k).attr("onclick","removeTR("+k+","+max+")");
            $tr0.find("#optionUp"+k).attr("onclick","optionUp("+k+","+max+")");
            $tr0.find("#optionUp"+k).attr("id","optionUp"+k);
            $tr0.find("#optionDown"+k).attr("onclick","optionDown("+k+","+max+")");
            $tr0.find("#optionDown"+k).attr("id","optionDown"+k);
        }


        layui.use('form', function(){
            var form = layui.form;
            form.render();
        });
    }else {
        layui.use('layer', function(){
            var layer = layui.layer;
            layer.msg('至少要有两个选项！');
        });
    }

}

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
        $tr.find("#optionAdd"+i).attr("onclick","addTR("+index+","+j+")");
        $tr.find("#optionAdd"+i).attr("id","optionAdd"+index);
        $tr.find("#optionRemove"+i).attr("onclick","removeTR("+index+","+j+")");
        $tr.find("#optionRemove"+i).attr("id","optionRemove"+index);
        $tr.find("#isTrue"+i).attr("value",index);
        $tr.find("#isTrue"+i).attr("id","isTrue"+index);
        $tr.find("#optionUp"+i).attr("onclick","optionUp("+index+","+j+")");
        $tr.find("#optionUp"+i).attr("id","optionUp"+index);
        $tr.find("#optionDown"+i).attr("onclick","optionDown("+index+","+j+")");
        $tr.find("#optionDown"+i).attr("id","optionDown"+index);
        $tr.next().find("#o_name"+index).attr("name","o_name"+i);
        $tr.next().find("#o_name"+index).attr("id","o_name"+i);
        $tr.next().find("#optionAdd"+index).attr("onclick","addTR("+i+","+j+")");
        $tr.next().find("#optionAdd"+index).attr("id","optionAdd"+i);
        $tr.next().find("#optionRemove"+index).attr("onclick","removeTR("+i+","+j+")");
        $tr.next().find("#optionRemove"+index).attr("id","optionRemove"+i);
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
        $tr.find("#optionAdd"+i).attr("onclick","addTR("+index+","+j+")");
        $tr.find("#optionAdd"+i).attr("id","optionAdd"+index);
        $tr.find("#optionRemove"+i).attr("onclick","removeTR("+index+","+j+")");
        $tr.find("#optionRemove"+i).attr("id","optionRemove"+index);
        $tr.find("#isTrue"+i).attr("value",index);
        $tr.find("#isTrue"+i).attr("id","isTrue"+index);
        $tr.find("#optionUp"+i).attr("onclick","optionUp("+index+","+j+")");
        $tr.find("#optionUp"+i).attr("id","optionUp"+index);
        $tr.find("#optionDown"+i).attr("onclick","optionDown("+index+","+j+")");
        $tr.find("#optionDown"+i).attr("id","optionDown"+index);
        $tr.prev().find("#o_name"+index).attr("name","o_name"+i);
        $tr.prev().find("#o_name"+index).attr("id","o_name"+i);
        $tr.prev().find("#optionAdd"+index).attr("onclick","addTR("+i+","+j+")");
        $tr.prev().find("#optionAdd"+index).attr("id","optionAdd"+i);
        $tr.prev().find("#optionRemove"+index).attr("onclick","removeTR("+i+","+j+")");
        $tr.prev().find("#optionRemove"+index).attr("id","optionRemove"+i);
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