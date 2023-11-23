import { useEffect, useRef, useState } from 'react'
import { Container as MapDiv, NaverMap } from 'react-naver-maps'



function Map() {
    return(
        <MapDiv
            style={{
                width: '100%',
                height: '600px',
            }}
        >
            <NaverMap />
        </MapDiv>
    );
}
export default Map;