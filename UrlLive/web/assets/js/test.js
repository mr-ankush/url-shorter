type = ['','info','success','warning','danger'];
demo = 
{
    showNotificationClipboard: function(from, align, msg)
    {
        color = Math.floor((Math.random() * 4) + 1);
        $.notify({
                icon: "ti-clipboard",
                message: "<b>"+msg+"</b> - powered by URL Live"

        },{
            type: type[color],
            timer: 100,
            placement: {
                from: from,
                align: align
            }
        });
    },
    showNotificationAlert: function(from, align, msg)
    {
        color = Math.floor((Math.random() * 4) + 1);
        $.notify({
                icon: "ti-clipboard",
                message: "<b>"+msg+"</b> - powered by URL Live"

        },{
            type: type[color],
            timer: 100,
            placement: {
                from: from,
                align: align
            }
        });
    },
    showNotificationOnline: function(from, align, msg)
    {
        color = Math.floor((Math.random() * 4) + 1);
        $.notify({
                icon: "ti-clipboard",
                message: "<b>"+msg+"</b>"

        },{
            type: type[color],
            timer: 100,
            placement: {
                from: from,
                align: align
            }
        });
    },
    showNotificationOffline: function(from, align, msg)
    {
        color = Math.floor((Math.random() * 4) + 1);
        $.notify({
                icon: "ti-clipboard",
                message: "<b>"+msg+"</b>"

        },{
            type: type[color],
            timer: 100,
            placement: {
                from: from,
                align: align
            }
        });
    }
}