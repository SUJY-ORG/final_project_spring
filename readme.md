# 목표
특정 지역의 주변 정보(편의점, 병원, 교육 시설, 음식점)를 바탕으로 주거공간으로써의 평가를 SpringAI, OpenAI를 활용해 출력한다.

# 구현
```java
@Controller
public class MainController {

	@Autowired private OpenAiChatModel openAiChatModel;

    // ...

    @PostMapping("/ai")
    public String ai(AIDto dto, Model model) {
        String prompt = "한국의 주거 공간으로써 어떤지 1~3문장으로 주관적으로 평가해줘. 내가 살 곳 반경 5km 내의 시설이야 ";
        prompt += "병원 " + dto.getHospital() + "개";
        prompt += "교육시설 " + dto.getEducation() + "개";
        prompt += "편의점 " + dto.getStore() + "개";
        prompt += "음식점 " + dto.getRestaurant() + "개";
        String openAiResponse = openAiChatModel.call(prompt);
        model.addAttribute("result", openAiResponse);
        return "ai";
    }

    // ...

}

```

![](/images/feat.png)

# 서비스 개선 계획
1. 네이버 지도에서 특정 위치를 클릭했을 때 반경 2~3km 내의 정보(병원, 교육 시설, 편의점, 음식점)를 API를 활용해 가져온다.
2. 위에서 받아온 정보를 바탕으로 생성형 AI를 활용해 주거공간으로써의 지역을 평가한다.
3. 회원가입 시 또는 회원가입 이후 회원 정보 수정 시 사용자의 나이, 성격, 취미 등을 입력받아 로그인 한 사용자에게는 맞춤형 평가를 제공한다.

![](/images/2.png)

# 느낀점
> 오승언  
강의를 보고 배워 SpringAI를 활용할 계획을 세웠습니다. 하지만 모든 내용을 깊이 이해할 필요 없이 단순히 활용만 할 줄 알면 된다는 생각에 강의를 모두 보지 않고 인터넷을 참고해서 구현했습니다. 이러한 습관이 비기능적인 요소에 집중하지 못하도록 하는 것 같다는 생각을 했습니다. 앞으로는 기능과 직접적으로 관련이 없는 개념일지라도 충실히 배워야하겠다는 다짐을 했습니다.



> 이지유
