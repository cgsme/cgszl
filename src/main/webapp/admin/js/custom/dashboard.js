jQuery(document).ready(function () {

    jQuery('#overviewselect, input:checkbox').uniform();

    ///// 时间选择器 /////
    jQuery("#datepickfrom, #datepickto").datepicker();

    ///// SLIM SCROLL /////
    jQuery('#scroll1').slimscroll({
        color: '#666',
        size: '10px',
        width: 'auto',
        height: '175px'
    });

    ///// 手风琴 /////
    jQuery('#accordion').accordion({autoHeight: false});

    ///// 简单的图表 /////
    var flash = [[new Date(2010, 1, 1), 1], [new Date(2010, 5, 1), -14], [new Date(2010, 10, 1), 5]];
    var html5 = [[new Date(2010, 2, 1), 13], [new Date(2010, 6, 1), 11], [new Date(2010, 12, 1), -7]];

    function showTooltip(x, y, contents) {
        jQuery('<div id="tooltip" class="tooltipflot">' + contents + '</div>').css({
            position: 'absolute',
            display: 'none',
            top: y + 5,
            left: x + 5
        }).appendTo("body").fadeIn(200);
    }


    var plot = jQuery.plot(jQuery("#chartplace"),
        [
            {data: flash, label: "Flash(x)", color: "#069"},
            {data: html5, label: "HTML5(x)", color: "#FF6600"}
        ],
        {
            series: {
                lines: {
                    show: true,
                    fill: true,
                    fillColor: {
                        colors: [
                            {opacity: 0.05},
                            {opacity: 0.15}
                        ]
                    }
                },
                xaxis: {
                    mode: "time",
                    timeformat: "%y/%m/%d"
                },
                points: {show: true}
            },
            legend: {position: 'nw'},
            grid: {
                hoverable: true, clickable: true, borderColor: '#ccc', borderWidth: 1, labelMargin: 10
            },
            yaxis: {min: 0, max: 15}
        });

    var previousPoint = null;
    jQuery("#chartplace").bind("plothover", function (event, pos, item) {
        jQuery("#x").text(pos.x.toFixed(2));
        jQuery("#y").text(pos.y.toFixed(2));

        if (item) {
            if (previousPoint != item.dataIndex) {
                previousPoint = item.dataIndex;

                jQuery("#tooltip").remove();
                var x = item.datapoint[0].toFixed(2),
                    y = item.datapoint[1].toFixed(2);

                showTooltip(item.pageX, item.pageY,
                    item.series.label + " of " + x + " = " + y);
            }

        } else {
            jQuery("#tooltip").remove();
            previousPoint = null;
        }

    });

    jQuery("#chartplace").bind("plotclick", function (event, pos, item) {
        if (item) {
            jQuery("#clickdata").text("You clicked point " + item.dataIndex + " in " + item.series.label + ".");
            plot.highlight(item.series, item.datapoint);
        }
    });


    ///// 将列从3列切换到2列列表 /////
    function rearrangeShortcuts() {
        if (jQuery(window).width() < 430) {
            if (jQuery('.shortcuts li.one_half').length == 0) {
                var count = 0;
                jQuery('.shortcuts li').removeAttr('class');
                jQuery('.shortcuts li').each(function () {
                    jQuery(this).addClass('one_half');
                    if (count % 2 != 0) jQuery(this).addClass('last');
                    count++;
                });
            }
        } else {
            if (jQuery('.shortcuts li.one_half').length > 0) {
                jQuery('.shortcuts li').removeAttr('class');
            }
        }
    }

    ////// 将列从3列切换到2列列表 //////
    rearrangeShortcuts();

    ///// 窗口大小改变时 /////
    jQuery(window).resize(function () {
        // 将列从3列切换到2列列表
        rearrangeShortcuts();
    });


});
