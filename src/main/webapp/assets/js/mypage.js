document.addEventListener("DOMContentLoaded", function () {

  // =========================
  // 탭 기능
  // =========================
  function openTab(evt, tabName) {
    const tabContent = document.getElementsByClassName("tab-content");
    const tabButtons = document.getElementsByClassName("tab-button");

    // 모든 탭 내용 숨기기
    for (let i = 0; i < tabContent.length; i++) {
      tabContent[i].style.display = "none";
    }

    // 모든 탭 버튼 active 제거
    for (let i = 0; i < tabButtons.length; i++) {
      tabButtons[i].className = tabButtons[i].className.replace(" active", "");
    }

    // 선택된 탭 표시
    document.getElementById(tabName).style.display = "block";
    evt.currentTarget.className += " active";
  }

  // =========================
  // 설정 드롭다운 메뉴
  // =========================
  const settingsButton = document.querySelector(".settings-icon");
  const settingsDropdown = document.querySelector(".settings-dropdown-menu");

  if (settingsButton && settingsDropdown) {

    // 버튼 클릭 시 드롭다운 토글
    settingsButton.addEventListener("click", (event) => {
      event.stopPropagation();
      settingsDropdown.classList.toggle("active");
    });

    // 드롭다운 내부 클릭 시 닫히지 않게
    settingsDropdown.addEventListener("click", (event) => {
      event.stopPropagation();
    });

    // 바깥 클릭 시 드롭다운 닫기
    document.addEventListener("click", () => {
      settingsDropdown.classList.remove("active");
    });
  }

});
