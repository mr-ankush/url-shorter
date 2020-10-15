  function buildSample() {
    var buildCode = $('#build-code').val();
    if ((buildCode.indexOf('theme=dark') != -1 ||
         buildCode.indexOf('theme="dark"') != -1) &&
         (buildCode.indexOf('layout=full') != -1 ||
         buildCode.indexOf('layout="full"') != -1)) {
      $('#build-sample').css('background', '#555');
    } else {
      $('#build-sample').css('background', '#FFF');
    }

    if (xssSanitize(buildCode)) {
      renderWidget(buildCode);
    } else {
      $('#build-sample').html('<strong>These aren\'t the XSS vulnerabilties ' +
          'you\'re looking for.');
    }
  }

  function xssSanitize(code) {
    code = code.replace('<div', '');
    code = code.replace('<\/div>', '');
    code = code.replace(/>\s*$/, '');
    if (code.match(/[\<\>]/)) {
      return false;
    }
    return true;
  }

  function build() {
    var sanitary = true;
    var js_file = '<script src="https://apis.google.com/js/platform.js"></script>\n\n';
    var template = '<div class="g-ytsubscribe" data-channel="*CHANNEL*" data-layout="*LAYOUT*" data-theme="*THEME*" data-count="*COUNT*"></div>';

    var layout = $('#build-layout').val();
    var theme = $('#build-theme').val();
    var count = $('#build-count').val();
    var hl = $('#build-hl').val();
    var width = 154;
    var height = 30;
    if (layout == "full") {
      width = 210;
      height = 51;
    }

    var channel = $('#build-name').val();
    if (!channel) {
      return;
    } else if (channel.match(/[\"\<\>]/)) {
      sanitary = false;
    } else if (channel.match(/^UC/) && channel.length == 24) {
      template = template.replace('data-channel=', 'data-channelid=');
    }

    if (theme == "dark" && layout == "full") {
      $('#build-sample').css('background', '#555');
    } else {
      if (theme == "default") {
        template = template.replace(' data-theme="*THEME*"', '');
      }
      $('#build-sample').css('background', '#FFF');
    }

    template = template.replace('__width', width);
    template = template.replace('__height', height);
    template = template.replace('*CHANNEL*', channel);
    template = template.replace('*LAYOUT*', layout);
    template = template.replace('*THEME*', theme);
    template = template.replace('*COUNT*', count);
    template = template.replace('__hl', hl);

    if (sanitary && xssSanitize(template)) {
      renderWidget(template);
      $('#build-code').val(js_file + template);
    } else {
      $('#build-sample').html('<strong>These aren\'t the XSS vulnerabilties ' +
          'you\'re looking for.');
    }
  }

  function renderWidget(html) {
    $('#build-sample').html(html);
    gapi.ytsubscribe.go('#build-sample');
  }

  // TODO (adiamondstein): Retrieve language list from API once it is available.
  function buildLanguageList() {
    var languages = {
      "af": "Afrikaans",
      "id": "Bahasa Indonesia",
      "ms": "Bahasa Malaysia",
      "ca": "CatalÃ ",
      "cs": "ÄŒeÅ¡tina",
      "da": "Dansk",
      "de": "Deutsch",
      "et": "Eesti",
      "en-GB": "English (UK)",
      "en-US": "English (US)",
      "es": "EspaÃ±ol (EspaÃ±a)",
      "es-419": "EspaÃ±ol (LatinoamÃ©rica)",
      "eu": "Euskara",
      "fil": "Filipino",
      "fr": "FranÃ§ais",
      "fr-CA": "FranÃ§ais (Canada)",
      "gl": "Galego",
      "hr": "Hrvatski",
      "zu": "IsiZulu",
      "is": "Ãslenska",
      "it": "Italiano",
      "sw": "Kiswahili",
      "lv": "LatvieÅ¡u valoda",
      "lt": "LietuviÅ³",
      "hu": "Magyar",
      "nl": "Nederlands",
      "no": "Norsk",
      "pl": "Polski",
      "pt-PT": "PortuguÃªs",
      "pt": "PortuguÃªs (Brasil)",
      "ro": "RomÃ¢nÄƒ",
      "sl": "SlovenÅ¡Äina",
      "sk": "SlovenskÃ½",
      "fi": "Suomi",
      "sv": "Svenska",
      "vi": "Tiáº¿ng Viá»‡t",
      "tr": "TÃ¼rkÃ§e",
      "bg": "Ð‘ÑŠÐ»Ð³Ð°Ñ€ÑÐºÐ¸",
      "ru": "Ð ÑƒÑÑÐºÐ¸Ð¹",
      "sr": "Ð¡Ñ€Ð¿ÑÐºÐ¸",
      "uk": "Ð£ÐºÑ€Ð°Ñ—Ð½ÑÑŒÐºÐ°",
      "el": "Î•Î»Î»Î·Î½Î¹ÎºÎ¬",
      "iw": "×¢×‘×¨×™×ª",
      "ur": "Ø§Ø±Ø¯Ùˆ",
      "ar": "Ø§Ù„Ø¹Ø±Ø¨ÙŠØ©",
      "fa": "ÙØ§Ø±Ø³ÛŒ",
      "mr": "à¤®à¤°à¤¾à¤ à¥€ ",
      "hi": "à¤¹à¤¿à¤¨à¥à¤¦à¥€ ",
      "bn": "à¦¬à¦¾à¦‚à¦²à¦¾ ",
      "gu": "àª—à«àªœàª°àª¾àª¤à«€",
      "ta": "à®¤à®®à®¿à®´à¯",
      "te": "à°¤à±†à°²à±à°—à±",
      "kn": "à²•à²¨à³à²¨à²¡",
      "ml": "à´®à´²à´¯à´¾à´³à´‚",
      "th": "à¸ à¸²à¸©à¸²à¹„à¸—à¸¢",
      "am": "áŠ áˆ›áˆ­áŠ›",
      "zh-CN": "ä¸­æ–‡ (ç®€ä½“)",
      "zh-TW": "ä¸­æ–‡ (ç¹é«”)",
      "zh-HK": "ä¸­æ–‡ (é¦™æ¸¯)",
      "ja": "æ—¥æœ¬èªž",
      "ko": "í•œêµ­ì–´",
    }
    document.write('<select id="build-hl" onchange="build();">');
    document.write('<option value="default">default</option>');
    for (var lang in languages) {
      document.write('<option value="' + lang + '">' + languages[lang] + '</option>');
    }
    document.write('</select>');
  }