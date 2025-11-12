import Layout from './Layout.jsx'
import '../css/leaderboard.scss'
import { useEffect, useState } from 'react';


const Leaderboard = () => {

    const [leaderboard, setLeaderboard] = useState(0);
    const [compNameList, setCompNameList] = useState([]);
    const [keyword, setKeyword] = useState("");
    const [isEmpty, setIsEmpty] = useState(false);

    useEffect(() => {
        fetch("http://localhost:8090/api/v1/leaderboard")
            .then((res) => res.json())
            .then((data) => {
                setLeaderboard(data.leaderboard || []);
                setCompNameList(data.compNameList || []);
                setKeyword(data.keyword || "");
                setIsEmpty(data.isEmpty || false);
            })
            .then((data) => { console.log("data : ", data) })
            .catch((err) => console.error(err));
    }, [keyword]);

    // 대회별 그룹핑
    const groupedByComp = compNameList.map((compName) => {
        const entries = leaderboard.filter((entry) => entry.compname === compName);
        return { compName, entries };
    });




    return (
            <main className="main">
                <section className="section-wrap">
                    <div>
                        <h1>리더보드 🏆</h1>
                        <p>상위권 참가자의 점수를 확인하세요.</p>
                    </div>

                    {/* <form className="search" onSubmit={onSearch}>
                        <input name="keyword" placeholder="검색어" defaultValue={keyword} />
                        <button className="btn" type="submit">검색</button>
                    </form> */}
                    
                    <div>
                        {groupedByComp.map(({ compName, entries }) => (
                            <div key={compName}>
                                <h3>{compName}</h3>
                                <div className="card" style={{ overflowX: "auto" }}>
                                    <table className="leaderboard"
                                        style={{ width: "100%", borderCollapse: "collapse" }}>
                                        <thead>
                                            <tr>
                                                <th>순위</th>
                                                <th>닉네임</th>
                                                <th>점수</th>
                                                <th>제출 횟수</th>
                                                <th>최근 제출일</th>
                                            </tr>
                                        </thead>
                                        <tbody className="leaderboardBody">
                                            {entries.map((entry) => (
                                                <tr key={entry.leaderBoardId}>
                                                    <td>{entry.comprank}</td>
                                                    <td>{entry.username}</td>
                                                    <tb>{entry.score}</tb>
                                                    <td>{entry.attempt}</td>
                                                    <td>{entry.submittedAt}</td>
                                                </tr>
                                            ))}
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        ))}

                        {/* 데이터 받아오는 거 확인 (기본)*/}
                        {/* <div style={{ marginTop: "1rem", background: "#f9f9f9", padding: "1rem" }}>
                                <h4>현재 상태 요약:</h4>
                                <ul>
                                    <li>leaderboard 길이: {leaderboard.length}</li>
                                    <li>compNameList: {compNameList.join(", ") || "없음"}</li>
                                    <li>keyword: {keyword || "없음"}</li>
                                    <li>isEmpty: {String(isEmpty)}</li>
                                </ul>
                            </div>

                            <pre style={{ background: "#eee", padding: "1rem", borderRadius: "8px" }}>
                                {JSON.stringify(leaderboard, null, 2)}
                            </pre> */}
                        {/* <h3>데이터 받아오는 거 확인2</h3>
                        {groupedByComp.map(({ compName, entries }) => (
                            <div key={compName}>
                                <h3>{compName}</h3>
                                <table>
                                    <tbody>
                                        {entries.map((entry) => (
                                            <tr key={entry.leaderBoardId}>
                                                <td>{entry.comprank}</td>
                                                <td>{entry.username}</td>
                                                <tb>{entry.score}</tb>
                                                <td>{entry.attempt}</td>
                                                <td>{entry.submittedAt}</td>
                                            </tr>
                                        ))}
                                    </tbody>
                                </table>
                            </div>
                        ))} */}
                    </div>

                </section>
            </main>
    )
}

export default Leaderboard

