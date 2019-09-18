module control(opcode, RegDst, Branch, MemRead, MemtoReg, ALUOp, MemWrite, ALUSrc, RegWrite);

	input	[5:0]	opcode;
	output	reg		RegDst, Branch, MemRead, MemtoReg, MemWrite, ALUSrc, RegWrite;
	output	reg	[1:0]	ALUOp;

	wire Rt, lw, sw, branch;


	//opcode
	//R-type: 0, lw,sw: 35 or 43, Branch: 4

	always@(1) begin

		if(opcode == 6'b000000) begin
				RegDst = 1;
				Branch = 0;
				MemRead = 0;
				MemtoReg = 0;
				ALUOp = 2'b10;
				MemWrite = 0;
				ALUSrc = 0;
				RegWrite = 1;
			end
		else if(opcode == 6'b100011) begin
				RegDst = 0;
				Branch = 0;
				MemRead = 1;
				MemtoReg = 1;
				ALUOp = 2'b00;
				MemWrite = 0;
				ALUSrc = 1;
				RegWrite = 1;
			end
		else if(opcode == 6'b101000) begin
				RegDst = 0;
				Branch = 0;
				MemRead = 0;
				MemtoReg = 0;
				ALUOp = 2'b00;
				MemWrite = 1;
				ALUSrc = 1;
				RegWrite = 0;
			end
		else if(opcode == 6'b000100) begin
				RegDst = 0;
				Branch = 1;
				MemRead = 0;
				MemtoReg = 0;
				ALUOp = 2'b01;
				MemWrite = 0;
				ALUSrc = 0;
				RegWrite = 0;
			end
		else begin
		end
	end
endmodule
