$('.star_rating > .star').click(function() {
  $(this).parent().children('span').removeClass('on');
  $(this).addClass('on').prevAll('span').addClass('on');
})

const wrapper_1 = $('#ingredients'); // 입력 필드를 포함하는 컨테이너 선택
const addButton_1 = $('#plus_btn_1'); // 추가 버튼 선택
const maxFields_1 = 30; // 최대 입력 필드 수 설정
let fieldCount_1 = 1; // 현재 입력 필드 수

// '추가하기' 버튼 클릭 시 이벤트
addButton_1.click(function(e) {
    e.preventDefault(); // 페이지 리로드 방지
    if (fieldCount_1 < maxFields_1) { // 최대 필드 수 체크
        fieldCount_1++; // 필드 수 증가
        // 새 입력 필드 추가
        wrapper_1.append(`
            <div class="ingredients_name hide_on_btn">
                <input class="main" type="text" placeholder="ex) 김치">
                <input class="sub" type="text" placeholder=" g,ml(단위)">
                <button class="minus_btn " type="button" style="cursor:pointer;"><i class="fa-solid fa-circle-xmark"></i></button>
              </div>
        `);
    }
});

// '삭제' 링크 클릭 시 이벤트
wrapper_1.on('click', '.minus_btn', function(e) {
    e.preventDefault(); // 페이지 리로드 방지
    $(this).parent('.ingredients_name').remove(); // 필드 제거
    fieldCount_1--; // 필드 수 감소
});

const wrapper_2 = $('#seasoning'); // 입력 필드를 포함하는 컨테이너 선택
const addButton_2 = $('#plus_btn_2'); // 추가 버튼 선택
const maxFields_2 = 30; // 최대 입력 필드 수 설정
let fieldCount_2 = 1; // 현재 입력 필드 수

// '추가하기' 버튼 클릭 시 이벤트
addButton_2.click(function(e) {
    e.preventDefault(); // 페이지 리로드 방지
    if (fieldCount_2 < maxFields_2) { // 최대 필드 수 체크
        fieldCount_2++; // 필드 수 증가
        // 새 입력 필드 추가
        wrapper_2.append(`
            <div class="seasoning_name hide_on_btn">
                <input class="main" type="text" placeholder=" ex) 고추장">
                <input class="sub" type="text" placeholder="  g,ml(단위)">
                <button class="minus_btn" type="button" style="cursor:pointer;"><i class="fa-solid fa-circle-xmark"></i></button>
              </div>
        `);
    }
});

// '삭제' 링크 클릭 시 이벤트
wrapper_2.on('click', '.minus_btn', function(e) {
    e.preventDefault(); // 페이지 리로드 방지
    $(this).parent('.seasoning_name').remove(); // 필드 제거
    fieldCount_2--; // 필드 수 감소
});

const wrapper_3 = $('.cooking_order'); // 입력 필드를 포함하는 컨테이너 선택
        const addButton_3 = $('.step_add_btn'); // 추가 버튼 선택
        const maxFields_3 = 30; // 최대 입력 필드 수 설정
        let fieldCount_3 = 1; // 현재 입력 필드 수
        let stepCounter = 2; // Step 번호 카운터 (초기 Step1은 이미 존재)

        // '추가하기' 버튼 클릭 시 이벤트
        addButton_3.click(function(e) {
            e.preventDefault(); // 페이지 리로드 방지
            if (fieldCount_3 < maxFields_3) { // 최대 필드 수 체크
                fieldCount_3++; // 필드 수 증가
                // 새 입력 필드 추가
                wrapper_3.append(`
                    <div class="make_step" id="make_step_${stepCounter}">
                        <div class="top_bar_step">
                            <div class="step_name_bar">
                                Step${stepCounter}
                            </div>
                            <div class="step_text_bar">
                                <textarea name="step_text[]" id="step_text_${stepCounter}" placeholder="ex) 소고기는 기름기를 떼어내고 적당한 크기로 썰어주세요."></textarea>
                            </div>
                            <div class="step_img_bar">
                                <div id="divStepPhotoBox_${stepCounter}" is_over="0">
                                    <img id="stepPhotoHolder_${stepCounter}" onclick="browseStepFile(${stepCounter})" src="https://github.com/Junsu-Ahn/cookers/assets/156152342/aed09051-deec-48df-a4eb-3114ba0b27d6" alt="이미지" style="width: 236; height: 236px; cursor:pointer">
                                </div>
                            </div>
                        </div>
                        <div class="top_bar_tip">
                            <div class="tip_name_bar"><i class="fa-solid fa-star"></i> 꿀 팁 <i class="fa-solid fa-star"></i></div>
                            <div class="tip_text_bar"><textarea name="tip_text[]" id="tip_text_${stepCounter}" placeholder="ex) 고기요리는 소금보다 설탕을 먼저 넣어야 단맛이 겉돌지 않고 육질이 부드러워져요"></textarea></div>
                        </div>
                        <button class="step_del_btn" type="button" value="삭제" onclick="removeStep(${stepCounter})"><i class="fa-solid fa-circle-xmark"></i></button>
                    </div>
                `);
                stepCounter++; // Step 번호 증가
            }
        });

        // '삭제' 링크 클릭 시 이벤트
        wrapper_3.on('click', '.step_del_btn', function(e) {
            e.preventDefault(); // 페이지 리로드 방지
            $(this).closest('.make_step').remove(); // 필드 제거
            fieldCount_3--; // 필드 수 감소
            updateStepNumbers(); // Step 번호 업데이트
        });

        // 모든 Step 번호를 업데이트하는 함수
        function updateStepNumbers() {
            let currentStep = 1;
            $('.make_step').each(function() {
                $(this).attr('id', `make_step_${currentStep}`);
                $(this).find('.step_name_bar').text(`Step${currentStep}`);
                $(this).find('.step_text_bar textarea').attr('id', `step_text_${currentStep}`);
                $(this).find('.step_img_bar div').attr('id', `divStepPhotoBox_${currentStep}`);
                $(this).find('.step_img_bar img').attr('id', `stepPhotoHolder_${currentStep}`).attr('onclick', `browseStepFile(${currentStep})`);
                $(this).find('.top_bar_tip textarea').attr('id', `tip_text_${currentStep}`);
                $(this).find('.step_del_btn').attr('onclick', `removeStep(${currentStep})`);
                currentStep++;
            });
            stepCounter = currentStep; // Step 번호 카운터 업데이트
        }