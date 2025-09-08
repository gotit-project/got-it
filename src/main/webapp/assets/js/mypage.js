// /assets/js/mypage.js (ES5 호환)
(function () {
  var qs = (function () {
    var result = {};
    var query = window.location.search.substring(1).split("&");
    for (var i = 0; i < query.length; i++) {
      var pair = query[i].split("=");
      if (pair.length === 2) {
        result[decodeURIComponent(pair[0])] = decodeURIComponent(pair[1]);
      }
    }
    return result;
  })();

  var current = (qs["tab"] === "scrap") ? "scrap" : "list";

  var tabButtons = document.querySelectorAll(".tabs .tab-button");
  var panels = {
    list: document.getElementById("my-posts"),
    scrap: document.getElementById("scrapped-posts")
  };

  function setActive(tab) {
    // 버튼 활성화
    for (var i = 0; i < tabButtons.length; i++) {
      var btn = tabButtons[i];
      var isActive = btn.getAttribute("data-tab") === tab;
      if (isActive) {
        btn.className = btn.className.replace(/\bactive\b/, "") + " active";
        btn.setAttribute("aria-selected", "true");
      } else {
        btn.className = btn.className.replace(/\bactive\b/, "");
        btn.setAttribute("aria-selected", "false");
      }
    }

    // 패널 전환
    for (var key in panels) {
      if (panels.hasOwnProperty(key)) {
        var el = panels[key];
        var active = key === tab;
        if (active) {
          if (el.className.indexOf("active") === -1) {
            el.className += " active";
          }
          el.setAttribute("aria-hidden", "false");
        } else {
          el.className = el.className.replace(/\bactive\b/, "");
          el.setAttribute("aria-hidden", "true");
        }
      }
    }
  }

  function updateURL(tab) {
    if (window.history && window.history.pushState) {
      var url = window.location.protocol + "//" + window.location.host + window.location.pathname + "?tab=" + tab;
      window.history.pushState({ tab: tab }, "", url);
    }
  }

  // 초기 상태
  setActive(current);

  // 클릭 핸들러 (기본 이동 막고 pushState)
  for (var i = 0; i < tabButtons.length; i++) {
    (function (btn) {
      btn.addEventListener("click", function (e) {
        if (e.preventDefault) e.preventDefault();
        var tab = btn.getAttribute("data-tab") === "scrap" ? "scrap" : "list";

        setActive(tab);
        updateURL(tab);
        // 서버로 새로 요청하려면 이거 사용
        // window.location.href = btn.href;
      });
    })(tabButtons[i]);
  }

  // 뒤로가기/앞으로가기 대응
  window.addEventListener("popstate", function (ev) {
    var tab = (ev.state && ev.state.tab) ? ev.state.tab :
      ((qs["tab"]) ? qs["tab"] : "list");
    setActive(tab === "scrap" ? "scrap" : "list");

  });
})();



// 프로필 이미지 변경 드롭다운 토글
document.addEventListener("DOMContentLoaded", () => {
  const settingsIcon = document.querySelector(".settings-icon");
  const dropdownMenu = document.querySelector(".settings-dropdown-menu");

  // 아이콘 클릭 시 토글
  settingsIcon.addEventListener("click", (e) => {
    e.stopPropagation(); // 이벤트 버블링 방지
    dropdownMenu.classList.toggle("active"); // CSS에서 .active로 열림 스타일 제어
  });

  // 바깥 클릭 시 닫기
  document.addEventListener("click", () => {
    dropdownMenu.classList.remove("active");
  });
});
