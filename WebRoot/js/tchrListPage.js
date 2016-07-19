$(function(){
	var $div=$('#adminTchrList');
	var currentPage = 0;
	var pageSize = 10;
	$div.bind('paging',function(){
		$div.find('section').hide().slice(currentPage*pageSize,(currentPage+1)*pageSize).show();
	});
	var sumRows = $('section').length;
	var sumPages = Math.ceil(sumRows/pageSize);
	var $pager = $('<div class="page"></div>');
	for(var pageIndex=0;pageIndex<sumPages;pageIndex++)
		{
			$('<a class="indexA"><span>'+(pageIndex+1)+'</span></a>').bind("click",{"newPage":pageIndex},function(event){
				currentPage=event.data["newPage"];
				$div.trigger("paging");
			}).appendTo($pager);
			$pager.append("");
		}
	$pager.insertAfter($div);
	$div.trigger("paging");
});