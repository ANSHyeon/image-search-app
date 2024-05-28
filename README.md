# image-search-app
unsplah API 사용한 이미지 검색 앱

## ✅주의사항
unsplah api baseUrl과 api key는 local.properties에 저장 후 사용했기에 GitHub에 공유되지 않습니다. 
<br/>
아래와 같은 형식으로 local.properties에 추가해야합니다.
```
unsplash_base_url="https://api.unsplash.com/"
unsplash_client_id="example"
```

## 📺︎ 작동화면

<div align="center">

| 무한 스트롤 | 자동 검색 | 네트워크 오류 |
| :---------------: | :---------------: | :---------------: |
| <img src="https://github.com/ANSHyeon/image-search-app/assets/127817240/5f1aed5d-c2e6-47c7-b34b-6c16949c30aa" align="center" width="250px"/> | <img src="https://github.com/ANSHyeon/image-search-app/assets/127817240/4231ecfd-9f92-4286-b377-95abda890946" align="center" width="250px"/> | <img src="https://github.com/ANSHyeon/image-search-app/assets/127817240/e6e08762-7420-457a-8212-6ba62fee5063" align="center" width="250px"/> |

</div>

<div align="center">

| 좋아요 반영 | 이미지 되돌리기 |
| :---------------: | :---------------: |
| <img src="https://github.com/ANSHyeon/image-search-app/assets/127817240/24c346da-4a93-4da3-a58e-d92c6a10cc15" align="center" width="250px"/> | <img src="https://github.com/ANSHyeon/image-search-app/assets/127817240/766596df-5425-4fa3-9bd2-8db7a70f08ac" align="center" width="250px"/> |

</div>

## 💬 고민사항

1. 피드화면과 즐겨찾기화면에서 좋아요 동기화
   - 이미지에 대한 좋아요 상태에 따라 피드화면과 즐겨찾기 화면이 동기화 되야한다.
   - 최초 피드화면에서 LaunchedEffect 블록에서 updateSearchResults 함수를 통해 즐겨찾기 화면에서 피드 화면으로 넘어오면 좋아요 상태를 갱신할 생각이였습니다.
   - 하지만 무의미한 화면 전환이 여러번 반복되면 불필요하게 이미지가 좋아요가 눌렸는지 여부를 반복해 연산해야 했기에 적합하지 않다고 생각했습니다.
   - 그래서 Flow의 combine 중간 연산자를 통해 네트워크로 받는 searchResults Flow와 favoriteImages Flow를 결합해 새로 발행될때 변경사항이 적용하도록 구현했습니다.
  
<img src="https://github.com/ANSHyeon/image-search-app/assets/127817240/cd18b960-1814-4b28-b950-0b747183075f" width="70%">

<br/>

<img src="https://github.com/ANSHyeon/image-search-app/assets/127817240/5f85dfb7-402c-49b8-8519-827b553028e8" width="70%">


<br/>
<br/>

2. 기술선택 이유

    < Coil >
   - Coil은 Kotlin 친화적이기 때문에 Jetpack Compose와 함께 사용시 일관되고 직관적으로 사용할 수 있습니다.
    
    < Kotlin Serialization >
   - Gson의 경우 data class의 default 값이 무시되는 오류가 발생하지만 Kotlin Serialization에서는 발생하지 않습니다.
   - @Serialization 어노테이션이 추가된 클래스를 직렬화 하기 때문에 간편하게 사용 가능하고, 컴파일 타임에 오류를 확인할 수 있습니다.
   - Kotlin 친화적이기 때문에 nullable type에 대한 런타임 오류발생 가능성이 줄어듭니다.














