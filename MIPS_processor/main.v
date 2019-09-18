
module	main(clk, res, button, seg_n, seg_v);

	input			clk, res;
	input		[7:0]	button;
	output reg	[7:0]	seg_n;
	output reg	[10:0]	seg_v;

	//segment 관련 크기는 매뉴얼 참고해서 수정요망


	reg		[31:0]	address;
	reg		[31:0]	Ins;

	//등등 해서 전체 데이터패스에서 사용되는 모든 중간값들을 레지스터로 등록

	always @(posedge clk) begin

		
		if(res == 1) begin


			address[6:0] = 32'h00000000;


		end


	instruction_memory(address, Ins);

	//같은 식으로 각 유닛들을 실행시킴 (전부 저렇게 함수를 호출해서 실행)

	address[31:0] = address[31:0] + 1;


	end

endmodule