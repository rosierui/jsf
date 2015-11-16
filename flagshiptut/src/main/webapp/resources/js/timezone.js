$(function() {
    if (document.cookie.indexOf("timezoneoffset") < 0) {
        date = new Date();
        var offset = date.getTimezoneOffset();
        var expires = date.setTime(date.getTime() + (60 * 60 * 1000));
        document.cookie = "timezoneoffset=" + offset + ";path=/" + "; secure;";
        document.cookie = "currtime=" + date.toString() + ";path=/" + "; secure;";
    }
});


