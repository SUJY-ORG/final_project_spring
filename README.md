<h1 align="center">
    <br>
    <img src="https://github.com/user-attachments/assets/3d15d1f2-3fc1-454c-a0f9-35ce312d246e" width="400">
    <br>
    Judge Around
    <br>
</h1>
<h4 align="center">부동산 매물을 조회하고 매물 주변 정보와 유저의 선호를 활용해 AI 주거지 평가를 받는 서비스</h4>
<img src="https://github.com/user-attachments/assets/052b2d56-a7c6-4589-93ab-7c9635e56e89">
<br>

## 컨텐츠 목록
- [소개](#-소개)
- [아키텍처](#-아키텍처)
- [활용 기술](#-활용기술)
- [Salt Hashing](#-Salt-Hashing)
- [Hashing 시간 지연](#-documentation)
- [Hashing 시간 고정](#-documentation)
- [개선안](#-documentation)

## 🎙️ 소개
**Judge Around**는 삼성 청년 SW 아카데미(이하 SSAFY)에서 진행한 최종 관통 프로젝트입니다. 회원가입 시 유저의 선호를 입력받고, 매물 조회 시 근처 1km 내의 정보와 선호를 조합해 AI 주거지 평가를 받는 서비스입니다. **보안강화**를 중점적으로 진행했으며, Salt Hashing, 다중 DB 운영, Hashing 시간 지연 및 고정을 적용했습니다.

## 🏗️ 아키텍처
<img src="https://github.com/user-attachments/assets/2dfcb943-f653-4c62-aa1c-4332e95a733a">

## 🧂 Salt Hashing
대소문자, 숫자, 특수문자 포함 10자리 비밀번호를 생성하는 경우의 수는 약 **53**경입니다. 또한 최근 MD5 해시 알고리즘 기준 RTX4090를 14개 병렬 연결 시 **초당 1500억**번 연산이 가능합니다. 즉, MD5 알고리즘으로 암호화된 비밀번호는 브루트포스 공격으로 약 **4.1**일만에 탈취가 가능합니다. 이러한 공격에 대응하기 위해 다음의 방식으로 보안을 강화했습니다.
- **Argon2id 알고리즘**: 메모리 사용량, 연산 반복 횟수, 병렬성을 파라미터로 조정이 가능한 알고리즘입니다. 향후 하드웨어의 발전에도 파라미터 조정으로 보안성을 유지할 수 있다는 장점으로 해당 알고리즘을 선택했습니다.
- **유저마다 다른 Salt 부여**: 해커가 해시 값과 알고리즘 정보를 탈취하더라도 평문 비밀번호를 알아내는 데에는 많은 시간이 소요됩니다. 또한, 특정 유저의 Salt를 탈취해 Rainbow Table을 만들었더라도 유저마다 다른 Salt를 가지고 있기 때문에 다른 유저의 계정을 해킹하기 위해서는 또다른 Rainbow Table을 만들어야 합니다.
- **다중 DB 운영**: 해시 값과 Salt를 서로 다른 DB에 저장하여 특정 DB가 해킹당하더라도 쉽게 공격하지 못하도록 할 수 있습니다.

## Hashing 시간 지연

