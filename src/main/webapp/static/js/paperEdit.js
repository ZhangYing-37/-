
var id;

function endEdit(co_id) {
	window.location.href = "/toPaperList?co_id="+co_id;
}

function setColor(i){
	id = i;
	$("#"+id).css("background-color","lightcyan");
	$("#"+id).css("cursor","pointer");
}

function removeColor(i){
	id = i;
	$("#"+id).css("background-color","white");
}
	
function toUpdate(){
	layui.use('layer', function(){
		var layer = layui.layer;
		layer.open({
			type: 2,
			scrollbar:false,
			resize:false,
			skin: 'layui-layer-rim',
		    area: ['500px', '350px'],
		    content: '/toUpdatePaper?type=0&sj_id=0'
		});
	});
}

function toUpdateSubjectDX1(sj_id) {
	layui.use('layer', function(){
		var layer = layui.layer;
		layer.open({
			type: 2,
			scrollbar:false,
			resize:false,
			skin: 'layui-layer-rim',
			area: ['500px', '500px'],
			content: '/toUpdatePaper?type=1&sj_id='+sj_id
		});
	});
}

function toUpdateSubjectDX(sj_id) {
	layui.use('layer', function(){
		var layer = layui.layer;
		layer.open({
			type: 2,
			scrollbar:false,
			resize:false,
			skin: 'layui-layer-rim',
			area: ['500px', '500px'],
			content: '/toUpdatePaper?type=2&sj_id='+sj_id
		});
	});
}

function toUpdateSubjectPD(sj_id) {
	layui.use('layer', function(){
		var layer = layui.layer;
		layer.open({
			type: 2,
			scrollbar:false,
			resize:false,
			skin: 'layui-layer-rim',
			area: ['500px', '400px'],
			content: '/toUpdatePaper?type=3&sj_id='+sj_id
		});
	});
}

function toUpdateSubjectTK(sj_id){
	layui.use('layer', function(){
		var layer = layui.layer;
		layer.open({
			type: 2,
			scrollbar:false,
			resize:false,
			skin: 'layui-layer-rim',
			area: ['500px', '350px'],
			content: '/toUpdatePaper?type=4&sj_id='+sj_id
		});
	});
}

function toUpdateSubjectJD(sj_id){
	layui.use('layer', function(){
		var layer = layui.layer;
		layer.open({
			type: 2,
			scrollbar:false,
			resize:false,
			skin: 'layui-layer-rim',
			area: ['500px', '350px'],
			content: '/toUpdatePaper?type=5&sj_id='+sj_id
		});
	});
}

	
/* function deleteProject(subject){
	$("#"+subject).remove();
} */

function gotoPage(aname) {
	window.location.href = "#"+aname;

}

function moveUp(i,j) {
	var $div = $("#paperS"+i);
	if (i!=1){
		layui.use('layer', function(){
			var layer = layui.layer;
			layer.load();
			setTimeout(function(){
				layer.closeAll('loading');
			}, 800);
		});

		$.ajax({
			url:"moveUpSubject",
			type:"get",
			data:{
				"s_code":i,
			},
			dataType:"text",
			success:function (data) {
				console.log("data="+data);
			}

		})
		$div.fadeOut().fadeIn();
		$div.prev().before($div);
		var a = i-1;
		$div.find(".s_code").text(a+"、");
		$div.next().find(".s_code").text(i+"、");
		$div.find("#moveUp"+i).attr("onclick","moveUp("+a+","+j+")");
		$div.find("#moveDown"+i).attr("onclick","moveDown("+a+","+j+")");
		$div.find("#moveUp"+i).attr("id","moveUp"+a);
		$div.find("#moveDown"+i).attr("id","moveDown"+a);
		$div.next().find("#moveUp"+a).attr("onclick","moveUp("+i+","+j+")");
		$div.next().find("#moveDown"+a).attr("onclick","moveDown("+i+","+j+")");
		$div.next().find("#moveUp"+a).attr("id","moveUp"+i);
		$div.next().find("#moveDown"+a).attr("id","moveDown"+i);
		$div.next().attr("id","paperS"+i);
		$div.attr("id","paperS"+a);
	}else {
		layui.use('layer', function(){
			var layer = layui.layer;
			layer.msg('已经到顶了！');
		});
	}
}

function moveDown(i,j) {
	var $div = $("#paperS"+i);
	if (i!=j){
		layui.use('layer', function(){
			var layer = layui.layer;
			layer.load();
			setTimeout(function(){
				layer.closeAll('loading');
			}, 800);
		});
		$.ajax({
			url:"moveDownSubject",
			type:"get",
			data:{
				"s_code":i,
			},
			dataType:"text",
			success:function (data) {
				console.log("data="+data);
			}
		})
		$div.fadeOut().fadeIn();
		$div.next().after($div);
		var a = i+1;
		$div.find(".s_code").text(a+"、");
		$div.prev().find(".s_code").text(i+"、");
		$div.find("#moveUp"+i).attr("onclick","moveUp("+a+","+j+")");
		$div.find("#moveDown"+i).attr("onclick","moveDown("+a+","+j+")");
		$div.find("#moveUp"+i).attr("id","moveUp"+a);
		$div.find("#moveDown"+i).attr("id","moveDown"+a);
		$div.prev().find("#moveUp"+a).attr("onclick","moveUp("+i+","+j+")");
		$div.prev().find("#moveDown"+a).attr("onclick","moveDown("+i+","+j+")");
		$div.prev().find("#moveUp"+a).attr("id","moveUp"+i);
		$div.prev().find("#moveDown"+a).attr("id","moveDown"+i);
		$div.prev().attr("id","paperS"+i);
		$div.attr("id","paperS"+a);
	}else {
		layui.use('layer', function(){
			var layer = layui.layer;
			layer.msg('已经到底了！');
		});
	}
}

function deleteSubject(sj_id,s_code) {
	layui.use('layer', function(){
		var layer = layui.layer;
		layer.msg('你确定要删除第'+s_code+'道题吗？', {
			time: 0 //不自动关闭
			,btn: ['删除', '取消']
			,yes: function(index){
				$.ajax({
					url:"deleteSubject",
					type:"get",
					data:{
						"sj_id":sj_id,
						"s_code":s_code
					},
					dataType:"text",
					success:function (data) {
						console.log("data="+data);
					}
				})
				window.location.reload();
				layer.close(index);
			}
		});
	});

}