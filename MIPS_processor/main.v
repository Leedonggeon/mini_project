
module	main(clk, res, button, seg_n, seg_v);

	input			clk, res;
	input		[7:0]	button;
	output reg	[7:0]	seg_n;
	output reg	[10:0]	seg_v;

	//segment ���� ũ��� �Ŵ��� �����ؼ� �������


	reg		[31:0]	address;
	reg		[31:0]	Ins;

	//��� �ؼ� ��ü �������н����� ���Ǵ� ��� �߰������� �������ͷ� ���

	always @(posedge clk) begin

		
		if(res == 1) begin


			address[6:0] = 32'h00000000;


		end


	instruction_memory(address, Ins);

	//���� ������ �� ���ֵ��� �����Ŵ (���� ������ �Լ��� ȣ���ؼ� ����)

	address[31:0] = address[31:0] + 1;


	end

endmodule