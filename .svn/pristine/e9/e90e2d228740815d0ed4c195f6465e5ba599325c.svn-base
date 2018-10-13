// 二维码显示
$(function () {
    $('.client-li').on('mouseover', function () {
        $(this).children('.code').show();
    });
    $('.client-li').on('mouseout', function () {
        $(this).children('.code').hide();
    });
    $('.client2-li').on('mouseover', function () {
        $(this).children('.code-weiXin').show();
    });
    $('.client2-li').on('mouseout', function () {
        $(this).children('.code-weiXin').hide();
    });

    // 分类选择
    $('.dropdown-menu-right li').on('click', function () {
        $('.btn-default').html($(this).text() + '<span class="caret"></span>');
    })


})






// 导航条
jQuery(document).ready(function () {

    var qcloud = {};

    $('[_t_nav]').hover(function () {

        var _nav = $(this).attr('_t_nav');

        clearTimeout(qcloud[_nav + '_timer']);

        qcloud[_nav + '_timer'] = setTimeout(function () {

            $('[_t_nav]').each(function () {

                $(this)[_nav == $(this).attr('_t_nav') ? 'addClass' : 'removeClass']('nav-up-selected');

            });

            $('#' + _nav).stop(true, true).slideDown(200);

        }, 150);

    }, function () {

        var _nav = $(this).attr('_t_nav');

        clearTimeout(qcloud[_nav + '_timer']);

        qcloud[_nav + '_timer'] = setTimeout(function () {

            $('[_t_nav]').removeClass('nav-up-selected');

            $('#' + _nav).stop(true, true).slideUp(200);

        }, 150);

    });

});