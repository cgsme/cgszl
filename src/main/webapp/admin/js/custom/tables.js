/*
 * 	Additional function for tables.html
 *	Written by ThemePixels	
 *	http://themepixels.com/
 *
 *	Copyright (c) 2012 ThemePixels (http://themepixels.com)
 *	
 *	Built for Amanda Premium Responsive Admin Template
 *  http://themeforest.net/category/site-templates/admin-templates
 */

jQuery(document).ready(function(){

	jQuery('.stdtablecb .checkall').click(function(){
		var parentTable = jQuery(this).parents('table');										   
		var ch = parentTable.find('tbody input[type=checkbox]');										 
		if(jQuery(this).is(':checked')) {
		
			//check all rows in table
			ch.each(function(){ 
				jQuery(this).attr('checked',true);
				jQuery(this).parent().addClass('checked');	//used for the custom checkbox style
				jQuery(this).parents('tr').addClass('selected');
			});
						
			//check both table header and footer
			parentTable.find('.checkall').each(function(){ jQuery(this).attr('checked',true); });
		
		} else {
			
			//uncheck all rows in table
			ch.each(function(){ 
				jQuery(this).attr('checked',false); 
				jQuery(this).parent().removeClass('checked');	//used for the custom checkbox style
				jQuery(this).parents('tr').removeClass('selected');
			});	
			
			//uncheck both table header and footer
			parentTable.find('.checkall').each(function(){ jQuery(this).attr('checked',false); });
		}
	});
	
	
	///// PERFORMS CHECK/UNCHECK BOX /////
	jQuery('.stdtablecb tbody input[type=checkbox]').click(function(){
		if(jQuery(this).is(':checked')) {
			jQuery(this).parents('tr').addClass('selected');	
		} else {
			jQuery(this).parents('tr').removeClass('selected');
		}
	});
	
	///// DELETE SELECTED ROW IN A TABLE /////
	jQuery('.deletebutton').click(function(){
		var tb = jQuery(this).attr('title');							// get target id of table								   
		var sel = false;												//initialize to false as no selected row
		var ch = jQuery('#'+tb).find('tbody input[type=checkbox]');		//get each checkbox in a table
		
		//check if there is/are selected row in table
		ch.each(function(){
			if(jQuery(this).is(':checked')) {
				sel = true;												//set to true if there is/are selected row
				jQuery(this).parents('tr').fadeOut(function(){
					jQuery(this).remove();								//remove row when animation is finished
				});
			}
		});
		
		if(!sel) alert('No data selected');								//alert to no data selected
	});
	
	
	///// DELETE INDIVIDUAL ROW IN A TABLE /////
	jQuery('.stdtable a.delete').click(function(){
		var c = confirm('Continue delete?');
		if(c) jQuery(this).parents('tr').fadeOut(function(){ 
			jQuery(this).remove();
		});
		return false;
	});
	
	///// GET DATA FROM THE SERVER AND INJECT IT RIGHT NEXT TO THE ROW SELECTED /////
	jQuery('.stdtable a.toggle').click(function(){
												
		//this is to hide current open quick view in a table 
		jQuery(this).parents('table').find('tr').each(function(){
			jQuery(this).removeClass('hiderow');
			if(jQuery(this).hasClass('togglerow'))
				jQuery(this).remove();
		});
		
		var parentRow = jQuery(this).parents('tr');
		var numcols = parentRow.find('td').length + 1;				//get the number of columns in a table. Added 1 for new row to be inserted				
		var url = jQuery(this).attr('href');
		
		//this will insert a new row next to this element's row parent
		parentRow.after('<tr class="togglerow"><td colspan="'+numcols+'"><div class="toggledata"></div></td></tr>');
		
		var toggleData = parentRow.next().find('.toggledata');
		
		parentRow.next().hide();
		
		//get data from server
		jQuery.post(url,function(data){
			toggleData.append(data);						//inject data read from server
			parentRow.next().fadeIn();						//show inserted new row
			parentRow.addClass('hiderow');					//hide this row to look like replacing the newly inserted row
			jQuery('input,select').uniform();
		});
				
		return false;
	});
		
		
	///// REMOVE TOGGLED QUICK VIEW WHEN CLICKING SUBMIT/CANCEL BUTTON /////	
	jQuery('.toggledata button.cancel, .toggledata button.submit').live('click',function(){
		jQuery(this).parents('.toggledata').animate({height: 0},200, function(){
			jQuery(this).parents('tr').prev().removeClass('hiderow');															 
			jQuery(this).parents('tr').remove();
		});
		return false;
	});
	
	
	
	jQuery('#dyntable').dataTable({
		"sPaginationType": "full_numbers"
	});

	////// jquery dataTable插件 //////
	jQuery('#allpoststable').dataTable({
        "sAjaxSource" : '/admin/getAllArticleList.action',
        /*"fnServerData": function (sSource, aoData, fnCallback) {
            var json = {
                "page": {
                    "start": aoData[3].value,
                    "length": aoData[4].value,
                },
                "search": {
                    "xb": $("#searchTitle").val()
                }
            };
            $.ajax({
                "dataType": 'json',
                "type": "POST",
                "url": "/admin/getAllArticleList.html",
                "contentType": "application/json; charset=utf-8",
                "data": JSON.stringify(json),
                "success": function (data) {
                    data.recordsTotal = data.page.recordsTotal;
                    data.recordsFiltered = data.page.recordsTotal;
                    fnCallback(data);
                }
            });
        },*/
        columns: ['文章标题','作者','状态','发布时间','点击量','所属分类'],
		sPaginationType: "full_numbers",
		aaSortingFixed: [[0,'asc']],
        dom: 'Bfrtip',
        buttons: ['colvis', 'excel', 'print'],
		aLengthMenu:[1, 5, 10, 20, 50],
		iDisplayLength:1,
        oLanguage: {
			"sDom": "<'top'fi>rt<'bottom'pl><'clear'>",
            "sProcessing": "正在加载中...",
            "sLengthMenu": "每页显示 _MENU_ 条记录",
            "sZeroRecords": "对不起，查询不到相关数据！",
            "sEmptyTable": "表中无数据存在！",
            "sInfo": "当前显示 _START_ 到 _END_ 条，共 _TOTAL_ 条记录",
            "sInfoFiltered": "数据表中共为 _MAX_ 条记录",
            "sSearch": "搜索",
            "oPaginate": {
                "sFirst": "首页",
                "sPrevious": "上一页",
                "sNext": "下一页",
                "sLast": "末页"
            }
        }, //多语言配置
		fnDrawCallback: function(oSettings) {
            jQuery('input:checkbox,input:radio').uniform();
			//jQuery.uniform.update();
        },
        aoColumns: [
            {"data": "title"},
            {"data": "authorId"},
            {"data": "status"},
            {"data": "created"},
            {"data": "hits"},
            {"data": "categories"},
        ]
    });

	
	///// TRANSFORM CHECKBOX AND RADIO BOX USING UNIFORM PLUGIN /////
	jQuery('input:checkbox,input:radio').uniform();
	
	
});